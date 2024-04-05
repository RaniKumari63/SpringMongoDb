package com.placement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.placement.constant.StatusConstants;
import com.placement.model.Candidate;
import com.placement.service.PlacementService;


import io.micrometer.common.util.StringUtils;

@Service
public class PlacementServiceImpl implements PlacementService {

	@Autowired
	MongoTemplate mongotemplate;

	@Override
	public String approveCandidate(String customerId) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(customerId));
		Candidate candidate = this.mongotemplate.findOne(query, Candidate.class);
		if (candidate == null) {

			return "Candidate for Id" + customerId + "Not Available";
		} else {
			Update update = new Update();
			update.set("status", StatusConstants.APPROVED);
			this.mongotemplate.updateFirst(query, update, Candidate.class);
		}
		return "Candidate Approved for Id" + customerId;
	}

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
			if(this.mongotemplate.count(query,Candidate.class)==0)
			{
				return new ResponseEntity<>("Email doesn't exist", HttpStatus.BAD_REQUEST);
			}
			query.addCriteria(Criteria.where("password").is(password));
			Candidate candidate = this.mongotemplate.findOne(query, Candidate.class);
			if (candidate != null) {
				return new ResponseEntity<>(candidate, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid credentials", HttpStatus.FORBIDDEN);
				
				
			}
			
			
	}

}
