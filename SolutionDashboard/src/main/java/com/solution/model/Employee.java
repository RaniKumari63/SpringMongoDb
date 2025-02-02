package com.solution.model;


import java.util.List;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solution.constant.CollectionConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection=CollectionConstants.EMPLOYEES)
public class Employee {
	@Id
	private String employeeId;
	private String employeeName;
	
	private String employeeEmailId;
	private String managerId;
	private String managerName;
	
	private String managerEmailId;
	private String phone;
	private Address address;
	
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String status;
	
}
