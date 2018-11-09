package com.ml.solarium.model;

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
		double anguloRadianes = Math.toRadians(this.anguloRotacion * dia);
		this.coordenadasPolares.setValorEjeX(Math.round(distancia * Math.cos(anguloRadianes) * 100d) / 100d);
		this.coordenadasPolares.setValorEjeY(Math.round(distancia * Math.sin(anguloRadianes) * 100d) / 100d);
	}
}
