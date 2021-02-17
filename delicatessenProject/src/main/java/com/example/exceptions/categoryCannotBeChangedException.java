package com.example.exceptions;

public class categoryCannotBeChangedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public categoryCannotBeChangedException() {
		super("changing product category is not allowed");
 	}
	 

}
