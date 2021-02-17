package com.example.exceptions;

public class customerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public customerNotFoundException() {
		super("customer was not found");
	}

}
