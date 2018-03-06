package com.ftn.isaproject.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import enums.EstablishmentType;
import model.Establishment;
import rest.EstablishmentController;
import service.EstablishmentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EstablishmentController.class)
public class EstablishmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EstablishmentService establishmentService;

	private List<Establishment> cinemas = new ArrayList<>(
			Arrays.asList(new Establishment("arena", "adresa1", "ns", EstablishmentType.CINEMA),
					new Establishment("cinestar", "adresa2", "ns", EstablishmentType.CINEMA)));

	@Test
	public void getAllEstablishmentsByType() throws Exception {

		Mockito.when(establishmentService.getAllEstablishmentsByType(EstablishmentType.CINEMA)).thenReturn(cinemas);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cinemas").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());

	}

	@Configuration
	public static class TestConfig {
		@Bean
		public EstablishmentController establishmentController() {
			return new EstablishmentController();
		}
	}
}
