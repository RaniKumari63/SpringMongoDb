package com.placement.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import com.placement.constant.StatusConstants;
import com.placement.model.Candidate;
import com.placement.model.CreateCandidateRequest;
import com.placement.model.UpdateCandidateRequest;
import com.placement.service.CandidateService;

import io.micrometer.common.util.StringUtils;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	MongoTemplate mongotemplate;

	@Override
	public String createCandidate(CreateCandidateRequest request) {
		// TODO Auto-generated method stub
		Random rand = new Random();

		String FirstNameSub = null;
		String LastNameSub = null;
		Criteria criteria = new Criteria();

		criteria.orOperator(

				Criteria.where("email")
						.regex(Pattern.compile(request.getEmail(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("phone")
						.regex(Pattern.compile(request.getPhone(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		Query query = new Query(criteria);

		Candidate candidate = this.mongotemplate.findOne(query, Candidate.class);

		if (candidate == null) {

			Candidate newcandidate = new Candidate();
			BeanUtils.copyProperties(request, newcandidate);
			if (StringUtils.isNotEmpty(newcandidate.getFirstName()) && newcandidate.getFirstName() != null) {
				if (newcandidate.getFirstName().length() > 3)

					FirstNameSub = newcandidate.getFirstName().substring(0, 3);
				else
					FirstNameSub = newcandidate.getFirstName();
			}
			if (StringUtils.isNotEmpty(newcandidate.getLastName()) && newcandidate.getLastName() != null) {
				if (newcandidate.getLastName().length() > 3)
					LastNameSub = newcandidate.getLastName().substring(0, 3);
				else
					LastNameSub = newcandidate.getLastName();

			}
			newcandidate.setId(FirstNameSub + LastNameSub + 1 + rand.nextInt(99));
			newcandidate.setStatus(StatusConstants.PENDING);
			newcandidate.setCreatedAt(new Date(System.currentTimeMillis()));
			this.mongotemplate.insert(newcandidate);
			return "Candidate successfully created with candidate id" + newcandidate.getId();

		} else {
			return "Candidate already exist";
		}
	}

	public List<?> getCandidates(String searchInput) {
		Query query = new Query();

		if (StringUtils.isNotEmpty(searchInput)) {

			query = this.getSearchQuery(searchInput);
		}

		List<Candidate> candidates = this.mongotemplate.find(query, Candidate.class);
		System.out.println(candidates);
		if (!CollectionUtils.isEmpty(candidates)) {
			return candidates;
		} else {
			return new ArrayList();
		}
	}

	public Candidate getCandidateById(String id) {
		Query query = new Query();
		if (StringUtils.isNotEmpty(id)) {

			query.addCriteria(Criteria.where("_id").is(id));
			Candidate candidate = this.mongotemplate.findOne(query, Candidate.class);
			if (candidate != null)
				return candidate;
			else
				return new Candidate();
		} else {
			return new Candidate();
		}
	}

	public boolean updateCandidate(UpdateCandidateRequest request) {
		boolean flag = false;
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(request.getCandidateId()));

		Candidate candidate = this.mongotemplate.findOne(query, Candidate.class);
		if (candidate != null) {
			if (request.getFirstName() != null)
				candidate.setFirstName(request.getFirstName());
			if (request.getLastName() != null)
				candidate.setLastName(request.getLastName());

			if (request.getEmail() != null)
				candidate.setEmail(request.getEmail());
			if (request.getPhone() != null)
				candidate.setPhone(request.getPhone());
			if (request.getAddress().getCity() != null)

				candidate.getAddress().setCity(request.getAddress().getCity());
			if (request.getAddress().getCountry() != null)
				candidate.getAddress().setCountry(request.getAddress().getCountry());
			if (request.getAddress().getLane() != null)
				candidate.getAddress().setLane(request.getAddress().getLane());
			if (request.getAddress().getState() != null)
				candidate.getAddress().setState(request.getAddress().getState());
			if (request.getAddress().getStreet() != null)
				candidate.getAddress().setStreet(request.getAddress().getStreet());
			if (request.getAddress().getZipCode() != null)
				candidate.getAddress().setZipCode(request.getAddress().getZipCode());
			if (request.getRole() != null)
				candidate.setRole(request.getRole());
			if (request.getVisatype() != null)
				candidate.setVisatype(request.getVisatype());
			candidate.setLastModifiedDate(new Date(System.currentTimeMillis()));
			this.mongotemplate.save(candidate);
			flag = true;

		}

		return flag;

	}

	public boolean deleteCustomer(String Id) {
		boolean flag = false;
		if (StringUtils.isNotEmpty(Id)) {
			Query query = new Query();
			query.addCriteria(Criteria.where("_Id").is(Id));
			Candidate candidate = this.mongotemplate.findOne(query, Candidate.class);
			if (candidate != null) {
				candidate.setStatus(StatusConstants.INACTIVE);
				this.mongotemplate.save(candidate);
				flag = true;
			} else {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	public Query getSearchQuery(String searchInput) {
		Query query = new Query();
		List<Criteria> criterias = new LinkedList();
		Criteria searchCriteria = new Criteria();
		searchCriteria.orOperator(
				Criteria.where("_id")
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
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
						.regex(Pattern.compile(searchInput, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
				Criteria.where("visatype")
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
