package com.suchiit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suchiit.exception.UserException;
import com.suchiit.model.User;
import com.suchiit.service.UserService;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/restapi/")
public class UserController {

	
@Autowired
private UserService userServiceImpl;

@PostMapping("/users")
public ResponseEntity<?> addUsers(@RequestBody User user) throws UserException
{

	try{userServiceImpl.createUser(user);
	
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	catch(ConstraintViolationException | UserException ex) {
	return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}


@GetMapping("/users")
public ResponseEntity<?> LoginUser(@RequestParam("email") String email,@RequestParam("password") String password) throws UserException
{
	User userlogin=userServiceImpl.LoginUser(email, password);
	if(userlogin!=null)
	{
		return new ResponseEntity<User>(userlogin,HttpStatus.OK);
	}
	else
	{
	return new ResponseEntity<>("Login Failure",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

/*
 * @GetMapping("/employess/{age}") List<User>
 * findByMatch(@PathVariable(value="age") int age) { //Group //MatchOperation
 * MatchOperation matchOperation=Aggregation.match(new Criteria("age").is(age));
 * SortOperation
 * sortOperation=Aggregation.sort(Sort.by(Sort.Direction.DESC,"age"));
 * Aggregation
 * aggregation=Aggregation.newAggregation(matchOperation,sortOperation);
 * 
 * AggregationResults
 * output=MongoTemplate.aggregate(aggregation,"user",User.class); return
 * output.getMappedResults(); }
 */
}
