package com.example.exceptions;

public class expirationDateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public expirationDateException() {
		super("check the validity of the expiration date");
	}
}
