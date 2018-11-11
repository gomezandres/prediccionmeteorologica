package com.ml.solarium.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;

@Entity
@ToString(callSuper = false, includeFieldNames = true)
public class Clima {

	@Id
	@Column(name = "dia", columnDefinition = "bigint")
	private Integer dia;

	@Column(name = "estado", nullable = false, columnDefinition = "varchar")
	private String estado;

	@Column(name = "intensidad", nullable = true, columnDefinition = "float")
	private double intensidad;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public double getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(double intensidad) {
		this.intensidad = intensidad;
	}

}
