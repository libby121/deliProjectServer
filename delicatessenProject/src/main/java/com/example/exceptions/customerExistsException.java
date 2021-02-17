package com.example.exceptions;

public class customerExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public customerExistsException() {
		super("customer's email address already exists");
	}

}
