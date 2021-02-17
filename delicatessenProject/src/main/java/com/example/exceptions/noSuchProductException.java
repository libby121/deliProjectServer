package com.example.exceptions;

public class noSuchProductException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public noSuchProductException() {
	super("product not found. verify your search");
}
}
