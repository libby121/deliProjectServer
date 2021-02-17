package com.example.exceptions;

public class productDuplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public productDuplicationException() {
super("product cannot be identical by title and category to an existing product");
}
}
