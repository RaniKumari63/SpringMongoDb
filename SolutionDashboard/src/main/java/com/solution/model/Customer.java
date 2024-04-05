package com.solution.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solution.constant.CollectionConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection=CollectionConstants.CUSTOMERS)
public class Customer {
	@Id
private String id;
private String firstName;
private String lastName;
private String email;
private String phone;
private Address address;
@JsonIgnore
@CreatedDate
private Date createdAt;
@JsonIgnore
@LastModifiedDate
private Date lastModifiedDate;
@JsonIgnore
private String password;
@JsonIgnore
private String status;
private String role;
}
