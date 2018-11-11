package com.ml.solarium.model;

import com.ml.solarium.util.Util;

import lombok.ToString;

@ToString(callSuper = false, includeFieldNames = true)
public class Planeta {

	private int distancia;

	private float anguloRotacion;

	private Coordenadas coordenadasPolares = new Coordenadas();

	public Planeta(int distancia, int anguloRotacion) {
		super();
		this.distancia = distancia;
		this.anguloRotacion = anguloRotacion;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public float getAnguloRotacion() {
		return anguloRotacion;
	}

	public void setAnguloRotacion(float anguloRotacion) {
		this.anguloRotacion = anguloRotacion;
	}

	public Coordenadas getCoordenadasPolares() {
		return coordenadasPolares;
	}

	public void setCoordenadasPolares(Coordenadas coordenadasPolares) {
		this.coordenadasPolares = coordenadasPolares;
	}

	public void actualizarPosicion(int dia) {
		this.coordenadasPolares
				.setValorEjeX(Util.calcularCoordenadaX(distancia, Math.toRadians(this.anguloRotacion * dia)));
		this.coordenadasPolares
				.setValorEjeY(Util.calcularCoordenadaY(distancia, Math.toRadians(this.anguloRotacion * dia)));
	}
}
