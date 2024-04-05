package com.suchiit.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="emp")
public class Employee {
private String id;
private String name;
private String firstName;
private String lastName;
private int age;
private String department;
}
