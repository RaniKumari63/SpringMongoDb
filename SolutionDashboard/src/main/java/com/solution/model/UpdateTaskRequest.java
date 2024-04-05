package com.solution.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {
	private String taskId;
	private String taskName;
	
	private String employeeId;
	
	private String projectName;
	
	private Date estimatedCompletionDate;
	private Date actualCompletionDate;
	

}
