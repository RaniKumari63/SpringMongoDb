package com.suchiit.exception;

public class TodoException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TodoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TodoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
 public static String NotFoundException(String id)
 {
	 return "Todo  with "+id+"not found"; 
	 
}
 
 public static String UserAlreadyExists()
 {
	 return "Todo already exists";
 }
}
