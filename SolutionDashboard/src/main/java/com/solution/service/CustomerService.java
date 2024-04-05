package com.solution.service;


import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.UpdateCustomerRequest;

public interface CustomerService {

	public String createCustomer(CreateCustomerRequest request);
	public Object getCustomers(String searchInput);

	public Customer getCustomerById(String id);

	public boolean updateCustomer(UpdateCustomerRequest request);

	public boolean deleteCustomer(String Id);
}
