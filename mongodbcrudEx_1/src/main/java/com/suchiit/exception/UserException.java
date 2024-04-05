package com.suchiit.exception;

public class UserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
 public static String NotFoundException(String id)
 {
	 return "User  with "+id+"not found"; 
	 
}
 
 public static String UserAlreadyExists()
 {
	 return "User already exists";
 }
}
