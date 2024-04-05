package com.solution;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.model.Address;
import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.LoginRequest;
import com.solution.model.UpdateCustomerRequest;
import com.solution.resources.CustomerResource;
import com.solution.resources.SolutionResource;
import com.solution.service.impl.CustomerServiceImpl;
import com.solution.service.impl.SolutionServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.solution")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { SolutionResourceTest.class })
public class SolutionResourceTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	SolutionServiceImpl solutionServiceImpl;

	@InjectMocks
	SolutionResource solutionResource;

	Customer customer1;

	Address address;

	Date date;

	LoginRequest request;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(solutionResource).build();
		address = new Address("lane7", "street7", "city7", "state7", "country", "zipcode6");
		date = new Date();

		customer1 = new Customer("aaa56aaa", "aaaa", "aaaa", "aaa@gmail.com", "87897", address, date, date, "aaa",
				"pending", "customer");
		request = new LoginRequest("aaa56aaa", "aaa");
	}

	@Test
	@Order(1)
	public void approveCustomerTest() throws Exception {

		String customerId = "aaa56aaa";

		when(solutionServiceImpl.approveCustomer(customerId)).thenReturn("Customer Approved for Id" + customerId);

		this.mockMvc.perform(post("/solution/solution/approvecustomer").param("customerId", "aaa56aaa"))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(2)
	public void rejectCustomerTest() throws Exception {

		String customerId = "aaa56aaa";

		when(solutionServiceImpl.rejectCustomer(customerId)).thenReturn("Customer Rejected for Id" + customerId);

		this.mockMvc.perform(post("/solution/solution/rejectcustomer").param("customerId", "aaa56aaa"))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(3)
	public void loginTest() throws Exception {

		String customerId = "aaa56aaa";
		ResponseEntity<?> response = new ResponseEntity<>(customer1, HttpStatus.OK);

		Mockito.<ResponseEntity<?>> when(solutionServiceImpl.loginAuthentication(customerId, "aaa")).thenReturn(response);

		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(request);

		this.mockMvc.perform(post("/solution/solution/login").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}

}
