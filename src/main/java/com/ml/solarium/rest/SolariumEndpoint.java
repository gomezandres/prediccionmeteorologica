package com.ml.solarium.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.ml.solarium.model.entity.Clima;
import com.ml.solarium.rest.response.Echo;
import com.ml.solarium.rest.response.ErrorResponse;
import com.ml.solarium.rest.response.Estado;
import com.ml.solarium.rest.response.Periodo;
import com.ml.solarium.service.SolariumService;
import com.ml.solarium.util.Constantes;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SolariumEndpoint {

	@Autowired
	private SolariumService solariumService;

	@GetMapping("/echo")
	@ApiOperation(value = "Test de conectividad")
	public Echo echo() {
		return new Echo("hello word!!");
	}

	@GetMapping("/clima")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Obtiene el clima de un dia especifico", response = Estado.class),
			@ApiResponse(code = 500, message = "Error generico", response = ErrorResponse.class) })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Estado obtenerClima(@RequestParam(name = "dia", required = true) int dia) {
		Clima clima = solariumService.obtenerClima(dia);
		return new Estado(clima.getDia(), clima.getEstado());
	}

	@GetMapping("/periodo")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Obtiene los periodos de acuerdo a una estado", response = Periodo.class),
			@ApiResponse(code = 500, message = "Error generico", response = ErrorResponse.class) })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Periodo obtenerCantidadPeriodo(
			@ApiParam(value = "[sequia,optimo,lluvia,normal]", required = true) @RequestParam(name = "periodo", required = true) String clima) {
		return new Periodo(solariumService.obtenerCantidadPeriodo(clima));
	}

	@GetMapping("/lluvia/maximaintensidad")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Obtiene el dia que habra lluvia con maxima intensidad", response = Clima.class),
			@ApiResponse(code = 500, message = "Error generico", response = ErrorResponse.class) })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Estado obtenerDiaMaximaIntensidad() {
		Clima clima = solariumService.obtenerDiaMaximaIntensidad();
		return new Estado(clima.getDia(), clima.getEstado());
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), Constantes.ERROR_GENERICO);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
