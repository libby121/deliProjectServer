package com.example.exceptions;

public class productNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public productNotFoundException() {
	super("no such product");
}

}
