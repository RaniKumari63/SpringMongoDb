package com.solution.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blocker {
	
	
	@JsonIgnore
	@CreatedDate
	private Date blockerCreatedAt;
	@JsonIgnore
	private String blockerCreatedBy;
	
	private String blockernotes;
}
