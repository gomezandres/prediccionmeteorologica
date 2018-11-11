package com.ml.solarium.rest.response;

import lombok.ToString;

@ToString(callSuper = false, includeFieldNames = true)
public class Periodo {

	private int cantidad;

	public Periodo(int cantidad) {
		super();
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
