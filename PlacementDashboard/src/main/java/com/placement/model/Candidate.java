package com.placement.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.placement.constant.CollectionConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = CollectionConstants.CANDIDATES)
public class Candidate {
	@Id
	private String id;
    private String firstName;
	private String lastName;
    private String email;
	private String phone;
	private Address address;
	// private String candidateId;
	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date lastModifiedDate;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String status;
	
	private String role;
	
	private String visatype;
}
