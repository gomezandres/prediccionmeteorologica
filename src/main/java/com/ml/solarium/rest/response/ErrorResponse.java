package com.ml.solarium.rest.response;

import java.util.Date;

public class ErrorResponse {

	private Date timestamp;
	private String mensaje;

	public ErrorResponse(Date timestamp, String mensaje) {
		super();
		this.timestamp = timestamp;
		this.mensaje = mensaje;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
