package com.suchiit.service.impl;

import java.net.UnknownServiceException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suchiit.exception.UserException;
import com.suchiit.model.User;
import com.suchiit.repo.UserRepository;
import com.suchiit.service.UserService;

import jakarta.validation.ConstraintViolationException;
@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserRepository userrepo;
	@Override
	public void createUser(User user) throws ConstraintViolationException,UserException {
		// TODO Auto-generated method stub
	Optional<User> userexists=userrepo.findByEmailAndPassword1(user.getEmail(),user.getPassword());
		
	
	if(userexists.isPresent()) {
			
			throw new UserException(UserException.UserAlreadyExists());
		}
		else
		{
			user.setCreatedAt(new Date(System.currentTimeMillis()));
			user.setStatus("active");
			userrepo.save(user);
			
		}
	}

	@Override
	public User LoginUser(String email, String password) throws UserException {
		// TODO Auto-generated method stub
		
		
		return userrepo.verifyEmailAndPassword(email, password);
	}

}
