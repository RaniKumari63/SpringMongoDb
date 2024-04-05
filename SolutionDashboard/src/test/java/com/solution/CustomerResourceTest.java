package com.solution;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.model.Address;
import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.UpdateCustomerRequest;
import com.solution.resources.CustomerResource;
import com.solution.service.impl.CustomerServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.solution")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { CustomerResourceTest.class })
public class CustomerResourceTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	CustomerServiceImpl customerServiceImpl;

	@InjectMocks
	CustomerResource customerResource;

	public List<Customer> customers;
	Customer customer1;
	Customer customer2;
	Customer customer3;
	Address address;
	CreateCustomerRequest request;
	UpdateCustomerRequest uprequest;
	Date date;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerResource).build();
		address = new Address("lane7", "street7", "city7", "state7", "country", "zipcode6");
		date = new Date();
		request = new CreateCustomerRequest("aaaa", "aaaa", "aaa@gmail.com", "87897", address, "aaa", "customer");
		uprequest = new UpdateCustomerRequest("aaa55aaa", "aaaa", "aaaa", "aaa@gmail.com", "87897", address,
				"customer");
		customer1 = new Customer("aaa56aaa", "aaaa", "aaaa", "aaa@gmail.com", "87897", address, date, date, "aaa",
				"approved", "customer");
		customer2 = new Customer("bbb56bbb", "bbbb", "bbb", "bbb@gmail.com", "778687", address, date, date, "bbbb",
				"approved", "customer");
		customer3 = new Customer("cccc56ccc", "cccc", "cccc", "ccc@gmail.com", "35355", address, date, date, "ccc",
				"approved", "customer");
		customers = new ArrayList<Customer>();

		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
	}

	@Test

	@Order(1)
	public void getCustomersTest() throws Exception {
		String searchInput = null;

		Mockito.<List<?>> when(customerServiceImpl.getCustomers(searchInput)).thenReturn(customers);
		this.mockMvc.perform(get("/solution/customer/getcustomers")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@Order(2)
	public void getCustomerTest() throws Exception {

		String customerId = "aaa56aaa";

		when(customerServiceImpl.getCustomerById(customerId)).thenReturn(customer1);

		this.mockMvc.perform(get("/solution/customer/getcustomer").param("id", "aaa56aaa")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value("aaa56aaa"))
				.andExpect(MockMvcResultMatchers.jsonPath(".firstName").value("aaaa"))
				.andExpect(MockMvcResultMatchers.jsonPath(".lastName").value("aaaa"))
				.andExpect(MockMvcResultMatchers.jsonPath(".email").value("aaa@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath(".phone").value("87897"))

				.andExpect(MockMvcResultMatchers.jsonPath(".address.lane").value("lane7"))
				.andExpect(MockMvcResultMatchers.jsonPath(".address.street").value("street7"))
				.andExpect(MockMvcResultMatchers.jsonPath(".address.city").value("city7"))
				.andExpect(MockMvcResultMatchers.jsonPath(".address.state").value("state7"))
				.andExpect(MockMvcResultMatchers.jsonPath(".address.country").value("country"))
				.andExpect(MockMvcResultMatchers.jsonPath(".address.zipCode").value("zipcode6"))

				.andExpect(MockMvcResultMatchers.jsonPath(".role").value("customer")).andDo(print());
	}

	@Test
	@Order(3)	
	public void createCustomerTest() throws Exception
	{
		
when(customerServiceImpl.createCustomer(request)).thenReturn("Customer Successfully created");
		
ObjectMapper mapper=new ObjectMapper();
String jsonbody=mapper.writeValueAsString(request);
	
	this.mockMvc.perform(post("/solution/customer/createcustomer")
			.content(jsonbody)
			.contentType(MediaType.APPLICATION_JSON))
	.andExpect(status().isOk())
	.andDo(print());
	}

	@Test
	@Order(4)
public void updateCustomerTest() throws Exception
{
		
	
	when(customerServiceImpl.updateCustomer(uprequest)).thenReturn(true);
	ObjectMapper mapper=new ObjectMapper();
	String jsonbody=mapper.writeValueAsString(uprequest);
		
		this.mockMvc.perform(put("/solution/customer/updatecustomer")
				.content(jsonbody)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
		}
	@Test
	@Order(5)
	public void deleteCustomerTest() throws Exception
	{
		String customerId="aaa56aaa";
		when(customerServiceImpl.deleteCustomer(customerId)).thenReturn(true);
		this.mockMvc.perform(delete("/solution/customer/deletecustomer").param("id", "aaa56aaa"))
		.andExpect(status().isOk())
		.andDo(print());
	}
		
}


