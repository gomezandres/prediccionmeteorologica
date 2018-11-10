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

import com.ml.solarium.rest.response.Clima;
import com.ml.solarium.rest.response.Echo;
import com.ml.solarium.rest.response.ErrorResponse;
import com.ml.solarium.service.SolariumService;
import com.ml.solarium.util.Constantes;

import io.swagger.annotations.ApiOperation;
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
	@ApiOperation(value = "Consulta el periodo de un clima")
	@ApiResponses({ @ApiResponse(code = 200, message = "Retornar el periodod", response = Clima.class),
			@ApiResponse(code = 500, message = "Cualquier error", response = void.class) })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Clima getClima(@RequestParam(name = "dia", required = true) int dia) {
		return solariumService.getClima(dia);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), Constantes.ERROR_GENERICO);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
