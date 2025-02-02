package com.placement.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateCandidateRequest {

	@NotNull(message="FirstName Cannot Be Null")
	private String firstName;
	@NotNull(message="LastName Cannot Be Null")
	private String lastName;
	
	private String email;
    @NotNull(message="PhoneNo Cannot Be Null")
    @Pattern(regexp ="[0-9]{10}$" ,message="phone no number is not valid")
	private String phone;
    
	private Address address;
	@NotNull(message="Password Cannot Be Null")
	private String password;
	
	@NotNull(message="Role Cannot Be Null")
	private String role;
	@NotNull(message="VisaType Cannot Be Null")
	private String visatype;
}
