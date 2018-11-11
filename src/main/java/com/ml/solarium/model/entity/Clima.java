package com.ml.solarium.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clima")
public class Clima {

	@Id
	@Column(name = "dia", columnDefinition = "integer")
	private Integer dia;

	@Column(name = "periodo", nullable = false, columnDefinition = "varchar")
	private String clima;

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

}
