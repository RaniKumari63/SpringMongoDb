package com.suchiit.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.suchiit.model.Employee;

@Repository
public class EmloyeeRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	public Employee save(Employee emp) {
		// TODO Auto-generated method stub
	
		return mongoTemplate.save(emp);
	}

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Employee.class);
	}

	public long delete(Employee emp) {
		// TODO Auto-generated method stub
		return mongoTemplate.remove(emp).getDeletedCount();
	}

	public List<Employee> findBySalary(int salary) {
		// TODO Auto-generated method stub
		Query query=new Query(Criteria.where("salary").gte(salary));
		return mongoTemplate.find(query, Employee.class);
	}

	public List<Employee> findByFirstName(String firstname) {
		// TODO Auto-generated method stub
		
		Query query=new Query(Criteria.where("firstname").regex("*"+firstname));
		return mongoTemplate.find(query, Employee.class);
	}

	public List<Employee> findByLastName(String lastname) {
		// TODO Auto-generated method stub
		Query query=new Query();
		query.addCriteria(Criteria.where("lastname").is(lastname));
		return mongoTemplate.find(query,Employee.class);
		
	}
	
	
}
