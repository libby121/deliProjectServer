package com.example.exceptions;

public class nosuchCartException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public nosuchCartException() {
		super("cart was not identified..");
	}
}
