package com.solution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {
	private String customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Address address;
	private String role;
	
}
