package com.ml.solarium.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ml.solarium.SolariumApplicationTests;

public class SolariumEndpointTest extends SolariumApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testEcho() throws Exception {
		mockMvc.perform(get("/echo")).andExpect(status().isOk());
	}

	@Test
	public void testObtenerClimaSEQUIA() throws Exception {
		mockMvc.perform(get("/clima?dia=0")).andExpect(status().isOk()).andExpect(jsonPath("$.clima").value("SEQUIA"))
				.andExpect(jsonPath("$.dia").value("0"));
	}

	@Test
	public void testObtenerClimaStatusInputA() throws Exception {
		mockMvc.perform(get("/clima?dia=A")).andExpect(status().is5xxServerError());
	}

	@Test
	public void testObtenerDiaMaximaIntensidadStatus200() throws Exception {
		mockMvc.perform(get("/lluvia/maximaintensidad")).andExpect(status().isOk());
	}

	@Test
	public void testPeriodoStatus500() throws Exception {
		mockMvc.perform(get("/periodo")).andExpect(status().is5xxServerError());
	}

	@Test
	public void testPeriodoEmpty1() throws Exception {
		mockMvc.perform(get("/periodo?periodo=A")).andExpect(status().isOk())
				.andExpect(jsonPath("$.cantidad").value("0"));
	}

	@Test
	public void testPeriodoLluvia() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/periodo?periodo=lluvia")).andExpect(status().isOk()).andReturn();
		Assert.assertNotEquals("{\"cantidad\": 0 }", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testPeriodoSequia() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/periodo?periodo=sequia")).andExpect(status().isOk()).andReturn();
		Assert.assertNotEquals("{\"cantidad\": 0 }", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testPeriodoNormal() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/periodo?periodo=normal")).andExpect(status().isOk()).andReturn();
		Assert.assertNotEquals("{\"cantidad\": 0 }", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testPeriodoOptimo() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/periodo?periodo=optimo")).andExpect(status().isOk()).andReturn();
		Assert.assertNotEquals("{\"cantidad\": 0 }", mvcResult.getResponse().getContentAsString());
	}
}
