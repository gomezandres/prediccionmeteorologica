package com.ml.solarium.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.ml.solarium.model.Coordenadas;
import com.ml.solarium.model.Planeta;
import com.ml.solarium.model.entity.Clima;
import com.ml.solarium.repository.ClimaRepository;
import com.ml.solarium.service.SolariumService;
import com.ml.solarium.util.Util;

@Service
public class SolariumServiceImpl implements SolariumService {

	private Planeta ferengi = new Planeta(500, -1);
	private Planeta betasoides = new Planeta(2000, -3);
	private Planeta vulcanos = new Planeta(1000, 5);
	private Coordenadas coordenadasSol = new Coordenadas(0, 0);

	@Autowired
	private ClimaRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void loadDatabase() {
		Clima climaEntity = new Clima();
		int dia = 0;
		while (dia < 3650) {

			ferengi.actualizarPosicion(dia);
			betasoides.actualizarPosicion(dia);
			vulcanos.actualizarPosicion(dia);

			double distanciaSolFerengi = Util.calcularDistancia(coordenadasSol, ferengi.getCoordenadasPolares());
			double distanciaFerengiVulcanos = Util.calcularDistancia(ferengi.getCoordenadasPolares(),
					vulcanos.getCoordenadasPolares());
			double distanciaVulcanosBetasoides = Util.calcularDistancia(vulcanos.getCoordenadasPolares(),
					betasoides.getCoordenadasPolares());
			double distanciaSolBBetasoides = Util.calcularDistancia(coordenadasSol, betasoides.getCoordenadasPolares());
			double distanciaFerengiBetasoides = Util.calcularDistancia(ferengi.getCoordenadasPolares(),
					betasoides.getCoordenadasPolares());

			if (isPeriodoSequia(distanciaSolFerengi, distanciaFerengiVulcanos, distanciaVulcanosBetasoides,
					distanciaSolBBetasoides)) {
				climaEntity.setEstado("SEQUIA");
			} else {
				if (isPeriodoCondicionesOptimas(distanciaFerengiBetasoides, distanciaFerengiVulcanos,
						distanciaVulcanosBetasoides)) {
					climaEntity.setEstado("OPTIMO");
				} else {
					if (isPeriodoLluvia(ferengi.getCoordenadasPolares(), betasoides.getCoordenadasPolares(),
							vulcanos.getCoordenadasPolares(), coordenadasSol)) {
						climaEntity.setEstado("LLUVIA");
						climaEntity.setIntensidad(
								(distanciaFerengiBetasoides + distanciaFerengiVulcanos + distanciaVulcanosBetasoides));
					} else {
						climaEntity.setEstado("NORMAL");
					}
				}
			}

			climaEntity.setDia(dia);
			repository.save(climaEntity);

			dia++;
		}
	}

	@Override
	public Clima obtenerClima(int dia) {
		return repository.findByDia(dia);
	}

	@Override
	public int obtenerCantidadPeriodo(String clima) {
		return repository.findAllByEstado(clima.toUpperCase()).size();
	}

	@Override
	public Clima obtenerDiaMaximaIntensidad() {
		return repository.findLluviaMaximaIntensidad().get(0);
	}

	public static boolean isPeriodoSequia(double distanciaSF, double distanciaFV, double distanciaVB,
			double distanciaSB) {
		return (distanciaSB == (distanciaSF + distanciaFV + distanciaVB));
	}

	public static boolean isPeriodoCondicionesOptimas(double distanciaFB, double distanciaFV, double distanciaVB) {
		return (distanciaFB == (distanciaFV + distanciaVB));
	}

	public static boolean isPeriodoLluvia(Coordenadas puntoA, Coordenadas puntoB, Coordenadas puntoC, Coordenadas sol) {
		if (Util.calcularAreaTriangulo(puntoA, puntoB, puntoC) >= 0)
			return Util.calcularAreaTriangulo(puntoA, puntoB, sol) >= 0
					&& Util.calcularAreaTriangulo(puntoB, puntoC, sol) >= 0
					&& Util.calcularAreaTriangulo(puntoC, puntoA, sol) >= 0;
		else
			return Util.calcularAreaTriangulo(puntoA, puntoB, sol) <= 0
					&& Util.calcularAreaTriangulo(puntoB, puntoC, sol) <= 0
					&& Util.calcularAreaTriangulo(puntoC, puntoA, sol) <= 0;
	}

}
