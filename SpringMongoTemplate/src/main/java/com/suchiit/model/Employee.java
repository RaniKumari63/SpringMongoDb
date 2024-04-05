package com.suchiit.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employeedoc")
public class Employee {
	@Id
private String id;
private String firstName;
private String lastName;
private float salary;
private Address address;
private Date joiningDate;
}
