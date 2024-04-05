package com.solution.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import com.solution.constant.StatusConstants;
import com.solution.model.CreateCustomerRequest;
import com.solution.model.Customer;
import com.solution.model.UpdateCustomerRequest;
import com.solution.service.CustomerService;

import io.micrometer.common.util.StringUtils;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String createCustomer(CreateCustomerRequest request) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		String firstNameSub = null;
		String lastNameSub = null;
		Criteria criteria = new Criteria();
		criteria.orOperator(
				Criteria.where("email")
						.regex(Pattern.compile(request.getEmail(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("phone")
						.regex(Pattern.compile(request.getPhone(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		Query query = new Query(criteria);
		Customer customer = this.mongoTemplate.findOne(query, Customer.class);
		if (customer == null) {
			Customer newCustomer = new Customer();
			BeanUtils.copyProperties(request, newCustomer);

			if (StringUtils.isNotEmpty(newCustomer.getFirstName()) && newCustomer.getFirstName() != null) {

				if (newCustomer.getFirstName().length() > 3)
					firstNameSub = newCustomer.getFirstName().substring(0, 3);
				else
					firstNameSub = newCustomer.getFirstName();

			}
			if(StringUtils.isNotEmpty(newCustomer.getLastName())&&newCustomer.getLastName()!=null)
			{
				if(newCustomer.getLastName().length()>3)
					lastNameSub=newCustomer.getLastName().substring(0, 3);
				else
					lastNameSub=newCustomer.getLastName();
			}
			newCustomer.setId(firstNameSub+lastNameSub+1+rand.nextInt(99));
			newCustomer.setStatus(StatusConstants.PENDING);
			newCustomer.setCreatedAt(new Date(System.currentTimeMillis()));
		this.mongoTemplate.insert(newCustomer);
		return "Candidate successfully created with Customer id "+newCustomer.getId();
		}
		else
		{
			return "Customer already exist";
		}

	}

	@Override
	public List<?> getCustomers(String searchInput) {
		// TODO Auto-generated method stub
		List<Customer> emptyCustomer=new ArrayList<Customer>();
		Query query=new Query();
		if (StringUtils.isNotEmpty(searchInput)) {

			query = this.getSearchQuery(searchInput);
		}
		
		List<Customer> customers=this.mongoTemplate.find(query, Customer.class);
		
		if(!CollectionUtils.isEmpty(customers))
		{
			return customers;
		}
		else
		{
			return emptyCustomer;
		}

	}

	@Override
	public Customer getCustomerById(String id) {
		Query query = new Query();
		if (StringUtils.isNotEmpty(id)) {

			query.addCriteria(Criteria.where("_id").is(id));
			
			Customer customer = this.mongoTemplate.findOne(query, Customer.class);
			
			if (customer != null)
				return customer;
			else
				return new Customer();
		} else {
			return new Customer();
		}
	}

	@Override
	public boolean updateCustomer(UpdateCustomerRequest request) {
		boolean flag = false;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(request.getCustomerId()));

		Customer customer = this.mongoTemplate.findOne(query, Customer.class);
		if (customer != null) {
			if (request.getFirstName() != null)
				customer.setFirstName(request.getFirstName());
			if (request.getLastName() != null)
				customer.setLastName(request.getLastName());

			if (request.getEmail() != null)
				customer.setEmail(request.getEmail());
			if (request.getPhone() != null)
				customer.setPhone(request.getPhone());
			if (request.getAddress().getCity() != null)

				customer.getAddress().setCity(request.getAddress().getCity());
			if (request.getAddress().getCountry() != null)
				customer.getAddress().setCountry(request.getAddress().getCountry());
			if (request.getAddress().getLane() != null)
				customer.getAddress().setLane(request.getAddress().getLane());
			if (request.getAddress().getState() != null)
				customer.getAddress().setState(request.getAddress().getState());
			if (request.getAddress().getStreet() != null)
				customer.getAddress().setStreet(request.getAddress().getStreet());
			if (request.getAddress().getZipCode() != null)
				customer.getAddress().setZipCode(request.getAddress().getZipCode());
			if (request.getRole() != null)
				customer.setRole(request.getRole());
			
			customer.setLastModifiedDate(new Date(System.currentTimeMillis()));
			this.mongoTemplate.save(customer);
			flag = true;

		}

		return flag;
	}

	@Override
	public boolean deleteCustomer(String Id) {
		boolean flag = false;
		if (StringUtils.isNotEmpty(Id)) {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(Id));
			Customer customer = this.mongoTemplate.findOne(query, Customer.class);
		
			if (customer != null) {
				customer.setStatus(StatusConstants.INACTIVE);
				this.mongoTemplate.save(customer);
				flag = true;
			} else {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	public Query getSearchQuery(String searchInput)
	{	Query query = new Query();
		List<Criteria> criterias=new LinkedList<>();
		Criteria searchCriteria=new Criteria();
		searchCriteria.orOperator(	
		Criteria.where("_id").regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("firstName")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("lastName")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),

Criteria.where("email")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),

Criteria.where("address.city")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("address.state")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("address.country")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("address.street")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("address.lane")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("address.zipCode")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("phone")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
Criteria.where("role")
		.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		criterias.add(searchCriteria);
		if (!CollectionUtils.isEmpty(criterias)) {
			Criteria criteria = new Criteria();
			criteria.andOperator(criterias.stream().toArray(Criteria[]::new));
			query.addCriteria(criteria);
		}
		return query;
			
		
	}
}
