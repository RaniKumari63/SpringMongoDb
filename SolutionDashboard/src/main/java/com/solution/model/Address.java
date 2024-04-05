package com.solution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String lane;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;

	
}
