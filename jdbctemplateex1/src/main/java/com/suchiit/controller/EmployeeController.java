package com.suchiit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suchiit.dao.EmployeeDao;
import com.suchiit.model.Employee;

@RestController
public class EmployeeController {
@Autowired
private EmployeeDao  eDAO;
@GetMapping("/employees")
public List<Employee> getEmployee()
{
	return eDAO.getAll();
	
}

@GetMapping("/employees/${id}")
public Employee getEmployeeById(@PathVariable int  id)
{
	return eDAO.getById(id);
	
}

@PostMapping("/employees/")
public String saveEmployee(@RequestBody Employee employee)
{
	return eDAO.save(employee)+"";
	
}
@PutMapping("/employees/{id}")
public String updateEmployee(@RequestBody Employee employee,@PathVariable int id)
{
	return eDAO.update(employee,id)+"no of rows effected";
	
}
@DeleteMapping("/employees/${id}")
public String deleteEmployeeById(@PathVariable int  id)
{
	return eDAO.delete(id)+"";
	
}
}
