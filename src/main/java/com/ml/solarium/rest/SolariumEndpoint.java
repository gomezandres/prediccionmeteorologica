package com.ml.solarium.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.solarium.rest.response.Echo;

@RestController
public class SolariumEndpoint {

	@GetMapping("/echo")
	public Echo echo() {
		return new Echo("hello word!!");
	}

}
