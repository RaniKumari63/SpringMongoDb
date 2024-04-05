package com.suchiit.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.suchiit.model.Employee;
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
