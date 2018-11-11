package com.ml.solarium.util;

import com.ml.solarium.model.Coordenadas;

public class Util {

	public static double calcularDistancia(Coordenadas puntoA, Coordenadas puntoB) {
		return Math.sqrt((Math.pow((puntoB.getValorEjeX() - puntoA.getValorEjeX()), 2)
				+ Math.pow((puntoB.getValorEjeY() - puntoA.getValorEjeY()), 2)));
	}

	public static double calcularAreaTriangulo(Coordenadas puntoA, Coordenadas puntoB, Coordenadas puntoC) {
		return (puntoA.getValorEjeX() - puntoC.getValorEjeX()) * (puntoA.getValorEjeY() - puntoC.getValorEjeY())
				- (puntoA.getValorEjeY() - puntoC.getValorEjeY()) * (puntoB.getValorEjeX() - puntoA.getValorEjeX());
	}

	public static double calcularCoordenadaX(int radio, double anguloRadianes) {
		return Math.round(radio * Math.cos(anguloRadianes) * 100d) / 100d;
	}

	public static double calcularCoordenadaY(int radio, double anguloRadianes) {
		return Math.round(radio * Math.sin(anguloRadianes) * 100d) / 100d;
	}
}
