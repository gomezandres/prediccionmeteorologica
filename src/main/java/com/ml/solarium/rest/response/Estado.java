package com.ml.solarium.rest.response;

public class Estado {

	private int dia;
	private String clima;

	public Estado(int dia, String clima) {
		super();
		this.dia = dia;
		this.clima = clima;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

}
