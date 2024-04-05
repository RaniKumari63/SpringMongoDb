package com.suchiit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.suchiit.model.Employee;

public interface EmployeeService {

	

	Employee save(Employee emp);

	List<Employee> getAll();

	Employee update(Employee emp);

	long delete(Employee emp);

	List<Employee> getBySalary(int salary);

	List<Employee> getByFirstName(String firstname);

	List<Employee> getByLastName(String lastname);

}
