package com.solution.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.UpdateCustomerRequest;
import com.solution.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/solution/customer")
public class CustomerResource {
	@Autowired
	CustomerService customerService;
	@PostMapping("/createcustomer")	
public ResponseEntity<?> createCustomer(@RequestBody  @Valid CreateCustomerRequest request)
{
	return new ResponseEntity<String>(this.customerService.createCustomer(request),HttpStatus.OK);
}

@GetMapping("/getcustomers")
public ResponseEntity<?> getCustomers(@RequestParam(required=false)String searchInput)
{
	return new ResponseEntity<>(this.customerService.getCustomers(searchInput),HttpStatus.OK);
}
@GetMapping("/getcustomer")
public ResponseEntity<?> getCustomer(@RequestParam String id) {
	return new ResponseEntity<Customer>(this.customerService.getCustomerById(id), HttpStatus.OK);
}

@PutMapping("/updatecustomer")
public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerRequest request) {
	boolean flag = this.customerService.updateCustomer(request);
	if (flag)
		return new ResponseEntity<>("Customer" + request.getCustomerId() + " is successfully updated", HttpStatus.OK);
	else
		return new ResponseEntity<>("No Customer found with Id- " + request.getCustomerId(), HttpStatus.NOT_FOUND);
}

@DeleteMapping("/deletecustomer")
public ResponseEntity<?> deleteCustomer(@RequestParam String id) {
	boolean flag = this.customerService.deleteCustomer(id);
	if (flag)
		return new ResponseEntity<>("Customer" + id + " is successfully deleted", HttpStatus.OK);
	else
		return new ResponseEntity<>("No Customer found with Id- " + id, HttpStatus.NOT_FOUND);
}


}
