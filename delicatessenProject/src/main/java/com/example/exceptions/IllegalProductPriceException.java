package com.example.exceptions;

public class IllegalProductPriceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalProductPriceException() {
		super("price has to be legal");
	}
}
