package com.ml.solarium.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.ml.solarium.model.Coordenadas;
import com.ml.solarium.model.Planeta;
import com.ml.solarium.rest.response.Clima;

@Service
public class SolariumServiceImpl implements SolariumService {

	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    System.out.println("hello world, I have just started up");
	}
	
	double maximoPerimetro = 0;
	int diaMaximoLluvia;

	public Clima getClima(int dia) {

		Clima clima = new Clima();
		clima.setDia(dia);
		Planeta ferengi = new Planeta(500, -1);
		Planeta betasoides = new Planeta(2000, -3);
		Planeta vulcanos = new Planeta(1000, 5);
		Coordenadas coordenadasSol = new Coordenadas(0, 0);

		ferengi.actualizarPosicion(dia);
		betasoides.actualizarPosicion(dia);
		vulcanos.actualizarPosicion(dia);

		double distanciaSolFerengi = calcularDistancia(coordenadasSol, ferengi.getCoordenadasPolares());
		double distanciaFerengiVulcanos = calcularDistancia(ferengi.getCoordenadasPolares(),
				vulcanos.getCoordenadasPolares());
		double distanciaVulcanosBetasoides = calcularDistancia(vulcanos.getCoordenadasPolares(),
				betasoides.getCoordenadasPolares());
		double distanciaSolBBetasoides = calcularDistancia(coordenadasSol, betasoides.getCoordenadasPolares());
		double distanciaFerengiBetasoides = calcularDistancia(ferengi.getCoordenadasPolares(),
				betasoides.getCoordenadasPolares());

		if (isPeriodoSequia(distanciaSolFerengi, distanciaFerengiVulcanos, distanciaVulcanosBetasoides,
				distanciaSolBBetasoides)) {
			clima.setPeriodo("SEQUIA");
		} else {
			if (isPeriodoCondicionesOptimas(distanciaFerengiBetasoides, distanciaFerengiVulcanos,
					distanciaVulcanosBetasoides)) {
				clima.setPeriodo("CONDICIONES OPTIMAS");
			} else {
				if (isPeriodoLluvia(ferengi.getCoordenadasPolares(), betasoides.getCoordenadasPolares(),
						vulcanos.getCoordenadasPolares(), coordenadasSol)) {
					clima.setPeriodo("LLUVIA");
					if ((distanciaFerengiBetasoides + distanciaFerengiVulcanos
							+ distanciaVulcanosBetasoides) > getMaximoPerimetro()) {
						setMaximoPerimetro(
								(distanciaFerengiBetasoides + distanciaFerengiVulcanos + distanciaVulcanosBetasoides));
						setDiaMaximoLluvia(dia);
					}
				} else {
					clima.setPeriodo("NORMAL");
				}
			}
		}

		return clima;
	}

	public boolean isPeriodoSequia(double distanciaSF, double distanciaFV, double distanciaVB, double distanciaSB) {
		return (distanciaSB == (distanciaSF + distanciaFV + distanciaVB));
	}

	public boolean isPeriodoCondicionesOptimas(double distanciaFB, double distanciaFV, double distanciaVB) {
		return (distanciaFB == (distanciaFV + distanciaVB));
	}

	public boolean isPeriodoLluvia(Coordenadas puntoA, Coordenadas puntoB, Coordenadas puntoC, Coordenadas sol) {
		if (calcularAreaTriangulo(puntoA, puntoB, puntoC) >= 0)
			return calcularAreaTriangulo(puntoA, puntoB, sol) >= 0 && calcularAreaTriangulo(puntoB, puntoC, sol) >= 0
					&& calcularAreaTriangulo(puntoC, puntoA, sol) >= 0;
		else
			return calcularAreaTriangulo(puntoA, puntoB, sol) <= 0 && calcularAreaTriangulo(puntoB, puntoC, sol) <= 0
					&& calcularAreaTriangulo(puntoC, puntoA, sol) <= 0;

	}

	public double calcularDistancia(Coordenadas puntoA, Coordenadas puntoB) {
		return Math.sqrt((Math.pow((puntoB.getValorEjeX() - puntoA.getValorEjeX()), 2)
				+ Math.pow((puntoB.getValorEjeY() - puntoA.getValorEjeY()), 2)));
	}

	double calcularAreaTriangulo(Coordenadas puntoA, Coordenadas puntoB, Coordenadas puntoC) {
		return (puntoA.getValorEjeX() - puntoC.getValorEjeX()) * (puntoA.getValorEjeY() - puntoC.getValorEjeY())
				- (puntoA.getValorEjeY() - puntoC.getValorEjeY()) * (puntoB.getValorEjeX() - puntoA.getValorEjeX());

	}

	public double getMaximoPerimetro() {
		return maximoPerimetro;
	}

	public void setMaximoPerimetro(double maximoPerimetro) {
		this.maximoPerimetro = maximoPerimetro;
	}

	public int getDiaMaximoLluvia() {
		return diaMaximoLluvia;
	}

	public void setDiaMaximoLluvia(int diaMaximoLluvia) {
		this.diaMaximoLluvia = diaMaximoLluvia;
	}

}
