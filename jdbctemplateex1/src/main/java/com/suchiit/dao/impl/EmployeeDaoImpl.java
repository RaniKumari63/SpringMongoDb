package com.suchiit.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.suchiit.dao.EmployeeDao;
import com.suchiit.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
@Autowired 
 JdbcTemplate jdbcTemplate;
	@Override
	public int save(Employee employee) {
		// TODO Auto-generated method st
		return jdbcTemplate.update("insert into tbl_employees(name,email,department) values(?,?,?)", new Object[] {employee.getName(),employee.getEmail(),employee.getDepartment()});
	}

	@Override
	public int update(Employee employee, int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("update tbl_employees set name=?,email=?,department=? where id=?) ", new Object[] {employee.getName(),employee.getEmail(),employee.getDepartment()});
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		
		return jdbcTemplate.update("delete from tbl_employees where id=?",id);
	
	}

	@Override
	public List<Employee> getAll() {
		return jdbcTemplate.query("select * from tbl_employees",new BeanPropertyRowMapper<Employee>(Employee.class));
	
	 }

	@Override
	public Employee getById(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select * from tbl_employees where id=?", new BeanPropertyRowMapper<Employee>(Employee.class),id);
	
	}

}
