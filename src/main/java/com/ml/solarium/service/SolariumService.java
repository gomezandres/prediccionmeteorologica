package com.ml.solarium.service;

import com.ml.solarium.exception.ClimaNotFoundException;
import com.ml.solarium.model.entity.Clima;

public interface SolariumService {

	public Clima obtenerClima(int dia) throws ClimaNotFoundException;

	public int obtenerCantidadPeriodo(String clima);

	public Clima obtenerDiaMaximaIntensidad();

}
