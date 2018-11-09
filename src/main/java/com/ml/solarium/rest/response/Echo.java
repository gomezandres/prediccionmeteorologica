package com.ml.solarium.rest.response;

public class Echo {

	private String mensaje;

	public Echo(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
