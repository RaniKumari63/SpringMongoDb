package com.suchiit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suchiit.model.Employee;
import com.suchiit.service.EmployeeService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
@Autowired
EmployeeService employeeservice;

@GetMapping("/")
public List<Employee> getAll()
{
	return employeeservice.getAll();
}
@PutMapping("/")
public Employee update(@RequestBody Employee emp)
{
	return employeeservice.update(emp);
}

@DeleteMapping("/")
public long delete(@RequestBody Employee emp)
{
	return employeeservice.delete(emp);
	
}
@GetMapping("/salary")
public List<Employee> getBySalary(@PathParam("salary") int salary)
{
	Query query=new Query();
	return employeeservice.getBySalary(salary);
}

@GetMapping("/lastname")
public List<Employee> getByLastName(@PathParam("lastname") String lastname)
{
	Query query=new Query();
	return employeeservice.getByLastName(lastname);
}

@GetMapping("/firstname")
public List<Employee> getByFirstName(@PathParam("firstname") String firstname)
{
	Query query=new Query();
	return employeeservice.getByFirstName(firstname);
}

}
