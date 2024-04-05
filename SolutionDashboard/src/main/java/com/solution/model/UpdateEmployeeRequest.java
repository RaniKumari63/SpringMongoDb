package com.solution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
	private String employeeId;
	private String employeeName;
	
	private String employeeEmailId;
	private String phone;
	private Address address;

	
	
}
