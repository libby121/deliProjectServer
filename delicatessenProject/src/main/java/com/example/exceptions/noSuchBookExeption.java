package com.example.exceptions;

public class noSuchBookExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public noSuchBookExeption() {
		super("book not found");
	}
}
