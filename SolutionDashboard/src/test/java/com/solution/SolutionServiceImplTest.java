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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mongodb.client.result.UpdateResult;
import com.solution.constant.StatusConstants;
import com.solution.model.Address;
import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.UpdateCustomerRequest;
import com.solution.service.impl.SolutionServiceImpl;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { SolutionServiceImplTest.class })
public class SolutionServiceImplTest {

	@InjectMocks
	private SolutionServiceImpl solutionServiceImpl;
	@Mock
	private MongoTemplate mongoTemplate;
	
	Customer customer1;
	
	Address address;
	
	Date date;
	@BeforeEach
	public void init() {
		address = new Address("lane7", "street7", "city7", "state7", "country", "zipcode6");
		date = new Date();
	
		customer1 = new Customer("aaa56aaa", "aaaa", "aaaa", "aaa@gmail.com", "87897", address, date, date, "aaa",
				"pending", "customer");
		
		

	}
	@Test
	@Order(1)
	public void approveCustomerTest() {
		
          String customerId="aaa56aaa";

		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer1);
		when(mongoTemplate.updateFirst(Mockito.any(Query.class), Mockito.any(Update.class), Mockito.eq(Customer.class))).thenReturn(null);
		
		assertEquals( "Customer Approved for Id" + customerId, solutionServiceImpl.approveCustomer(customerId));
	}
	
	@Test
	@Order(2)
	public void rejectCustomerTest() {
		
          String customerId="aaa56aaa";

		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer1);
		when(mongoTemplate.updateFirst(Mockito.any(Query.class), Mockito.any(Update.class), Mockito.eq(Customer.class))).thenReturn(null);
		
		assertEquals( "Customer Rejected for Id" + customerId, solutionServiceImpl.rejectCustomer(customerId));
	}
	
	@Test
	@Order(2)
	public void loginAuthenticationTest() {
		
          String customerId="aaa56aaa";
        
		when(mongoTemplate.count(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(1l);
		when(mongoTemplate.findOne(Mockito.any(Query.class), Mockito.eq(Customer.class))).thenReturn(customer1);
		
		assertEquals( new ResponseEntity<>(customer1, HttpStatus.OK), solutionServiceImpl.loginAuthentication(customerId, "aaa"));
	}
	

}
