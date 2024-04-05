package com.solution.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.solution.constant.StatusConstants;
import com.solution.model.Customer;
import com.solution.service.SolutionService;

import io.micrometer.common.util.StringUtils;
@Service
public class SolutionServiceImpl implements SolutionService {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String approveCustomer(String customerId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(customerId));
		Customer customer = this.mongoTemplate.findOne(query, Customer.class);
		if (customer == null) {

			return "Customer for Id" + customerId + "Not Available";
		} else {
			Update update = new Update();
			update.set("status", StatusConstants.APPROVED);
			this.mongoTemplate.updateFirst(query, update, Customer.class);
		}
		return "Customer Approved for Id" + customerId;
	}

	@Override
	public String rejectCustomer(String customerId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(customerId));
		Customer customer = this.mongoTemplate.findOne(query, Customer.class);
		if (customer == null) {

			return "Customer for Id" + customerId + "Not Available";
		} else {
			Update update = new Update();
			update.set("status", StatusConstants.REJECTED);
			this.mongoTemplate.updateFirst(query, update, Customer.class);
		}
		return "Customer Rejected for Id" + customerId;
	}

	@Override
	public ResponseEntity<?> loginAuthentication(String searchInput, String password) {
		
		if (StringUtils.isEmpty(searchInput) || (StringUtils.isEmpty(password))) {
			
			return new ResponseEntity<>("Username or Password should not be blank", HttpStatus.BAD_REQUEST);
		}

		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("email").is(searchInput),
				            Criteria.where("phone").is(searchInput),
				            Criteria.where("_id").is(searchInput));
			Query query=new Query();
			query.addCriteria(criteria);
			query.addCriteria( Criteria.where("status").is(StatusConstants.APPROVED));
			if(this.mongoTemplate.count(query,Customer.class)==0)
			{
				return new ResponseEntity<>("Email doesn't exist", HttpStatus.BAD_REQUEST);
			}
			query.addCriteria(Criteria.where("password").is(password));
			Customer customer = this.mongoTemplate.findOne(query, Customer.class);
			if (customer != null) {
				return new ResponseEntity<>(customer, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);
				
				
			}
	}

}
