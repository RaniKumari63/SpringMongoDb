package com.suchiit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suchiit.model.Employee;
import com.suchiit.repo.EmloyeeRepository;
import com.suchiit.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	
	@Autowired
	EmloyeeRepository employeerepo;

	@Override
	public Employee save(Employee emp) {
		// TODO Auto-generated method stub
		emp.setJoiningDate(new Date(System.currentTimeMillis()));
		return employeerepo.save(emp);
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeerepo.getAll();
	}

	@Override
	public Employee update(Employee emp) {
		// TODO Auto-generated method stub
		return employeerepo.save(emp);
	}

	@Override
	public long delete(Employee emp) {
		// TODO Auto-generated method stub
		return employeerepo.delete(emp);
	}

	@Override
	public List<Employee> getBySalary(int salary) {
		// TODO Auto-generated method stub
		return employeerepo.findBySalary(salary);
	}

	@Override
	public List<Employee> getByFirstName(String firstname) {
		// TODO Auto-generated method stub
		return employeerepo.findByFirstName(firstname);
	}

	@Override
	public List<Employee> getByLastName(String lastname) {
		// TODO Auto-generated method stub
		return employeerepo.findByLastName(lastname);
	}
}
