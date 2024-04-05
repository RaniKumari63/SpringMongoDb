package com.suchiit.dao;

import java.util.List;

import com.suchiit.model.Employee;

public interface EmployeeDao {
public  int save(Employee employee);
public  int update(Employee employee,int id);
public int delete(int id);
List<Employee> getAll();
Employee getById(int id);
}
