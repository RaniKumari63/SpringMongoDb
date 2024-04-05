package com.solution;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.solution.model.Address;
import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.UpdateCustomerRequest;
import com.solution.resources.CustomerResource;
import com.solution.service.impl.CustomerServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { Test_CustomerResouce.class })
public class Test_CustomerResouce {
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
	public void init() {
		address = new Address("lane7", "street7", "city7", "state7", "country", "zipcode6");
		date = new Date();
		request = new CreateCustomerRequest("aaaa", "aaaa", "aaa@gmail.com", "87897", address, "aaa", "customer");
     uprequest= new UpdateCustomerRequest("aaa55aaa", "aaaa", "aaaa", "aaa@gmail.com", "87897",
				address, "customer");
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
	public void getCustomersTest() {
		
		String searchInput = null;

		Mockito.<List<?>> when(customerServiceImpl.getCustomers(searchInput)).thenReturn(customers);
		ResponseEntity<?> res = customerResource.getCustomers(searchInput);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(customers, res.getBody());

	}
	
	@Test
	@Order(2)
public void getCustomerTest()
{
		
	String customerId="aaa56aaa";
	when(customerServiceImpl.getCustomerById(customerId)).thenReturn(customer1);
	ResponseEntity<?> res = customerResource.getCustomer(customerId);
	assertEquals(HttpStatus.OK, res.getStatusCode());
	assertEquals(customer1, res.getBody());
}
	
	@Test
	@Order(3)	
	public void createCustomerTest()
	{
		
when(customerServiceImpl.createCustomer(request)).thenReturn("Customer Successfully created");
		
ResponseEntity<?> res = customerResource.createCustomer(request);	
assertEquals(HttpStatus.OK, res.getStatusCode());
assertEquals("Customer Successfully created", res.getBody());	

	
	
	}
	@Test
	@Order(4)
public void updateCustomerTest()
{
		
	
	when(customerServiceImpl.updateCustomer(uprequest)).thenReturn(true);
	ResponseEntity<?> res = customerResource.updateCustomer(uprequest);
	assertEquals(HttpStatus.OK, res.getStatusCode());
	assertEquals("customer successfully updated", res.getBody());
}
	@Test
	@Order(5)
	public void deleteCustomerTest()
	{
		String customerId="aaa56aaa";
		when(customerServiceImpl.deleteCustomer(customerId)).thenReturn(true);
		ResponseEntity<?> res = customerResource.deleteCustomer(customerId);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals("customer successfully deleted", res.getBody());
	}
		
	
	
}
