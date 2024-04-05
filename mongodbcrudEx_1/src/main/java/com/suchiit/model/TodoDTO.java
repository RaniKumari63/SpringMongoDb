package com.suchiit.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos1")
public class TodoDTO {
@Id
private String id;
@NotNull(message="todo cannot be null")
private String todo;
@DateTimeFormat(pattern = "yyyy-mm-dd")
private Date startDate;
@NotNull(message="description cannot be null")
private String description;
@NotNull(message="completed cannot be null")
private Boolean completed;
@CreatedDate
private Date createdAt;
@LastModifiedDate
private Date updatedAt;


}
