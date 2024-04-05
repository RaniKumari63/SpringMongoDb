package com.suchiit.service;

import com.suchiit.exception.UserException;
import com.suchiit.model.User;

import jakarta.validation.ConstraintViolationException;

public interface UserService {
public void createUser(User user) throws ConstraintViolationException,UserException;
public User LoginUser(String email,String password) throws UserException;
 

}
