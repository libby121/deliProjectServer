package com.example.exceptions;

public class customerDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public customerDoesNotExistException() {
		super("customer Cannot be recognised by this email");
	}

}
