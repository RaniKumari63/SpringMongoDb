package com.suchiit.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
@Id
	private String userid;
@NotBlank(message="Username is null")
	private String username;
@NotBlank(message="Email is null")
@Email
	private String email;
@NotBlank(message="firstName is null")
	private String firstName;
@NotBlank(message="lastName is null")
	private String lastName;
@NotBlank(message="password is null")
	private String password;
@NotBlank(message="description is null")
	private String description;
@NotBlank(message="address is null")
	private String address;
@NotNull(message="mobileNo is null")
	private long moblieNo;
	@CreatedDate
	private Date createdAt;
	@LastModifiedDate
	private Date updatedAt;
	private String status;
}
