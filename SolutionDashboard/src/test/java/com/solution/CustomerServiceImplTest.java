package com.solution;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;


import com.solution.model.Address;
import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.UpdateCustomerRequest;
import com.solution.service.impl.CustomerServiceImpl;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { CustomerServiceImplTest.class })
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	@Mock
	private MongoTemplate mongoTemplate;

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

		

		when(mongoTemplate.find(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customers);

		assertEquals(3, customerServiceImpl.getCustomers("aaaa").size());
		
	}

	@Test
	@Order(2)
	public void getCustomerByIdTest() {
		

		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer1);

		assertEquals("aaa56aaa", customerServiceImpl.getCustomerById("aaa56aaa").getId());
		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer2);
		assertEquals("bbb56bbb", customerServiceImpl.getCustomerById("bbb56bbb").getId());
	}

	@Test
	@Order(3)
	public void createCustomerTest() {
		
		
		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer1);
		
		when(mongoTemplate.insert(customer1)).thenReturn(customer1);
		

	
		assertEquals("successfully", customerServiceImpl.createCustomer(request));
		
	}

	@Test
	@Order(4)
	public void upDateCustomerTest() {
		Boolean flag = true;
		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer1);
		when(mongoTemplate.save(customer1)).thenReturn(customer1);
		
		assertEquals(true, customerServiceImpl.updateCustomer(uprequest));
	}

	@Test
	@Order(5)
	public void deleteCustomerTest() {
		
		String id = "aaa55aaa";
		Customer customer = new Customer("aaa55aaa", "aaaa", "aaaa", "aaa@gmail.com", "87897", address, date, date,
				"aaa", "approved", "customer");
		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer);
		when(mongoTemplate.save(customer)).thenReturn(customer);
		assertEquals(true, customerServiceImpl.deleteCustomer(id));
	}
	@Test
	@Order(6)
	public void getSearchQuery() {
		
		String searchInput="aaa55aaa";
		
		assertEquals(new Query(), customerServiceImpl.getSearchQuery(searchInput));
	}

}
