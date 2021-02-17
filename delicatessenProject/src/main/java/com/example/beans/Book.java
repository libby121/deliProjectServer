package com.example.beans;

import javax.persistence.DiscriminatorValue;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
 
@JsonTypeName(value = "book")//needed??
 

@Entity
@DiscriminatorValue("books")
 
public class Book extends Product {

	public Book(String title, double price, Category category,
			int amount, String author, int year, String language) {
		
		super(title, price, category, amount);
		 
		this.author = author;
		this.year = year;
		this.language = language;
	}

	 
 	 

	private String author;
	private int year;
	private String language;
	
	public Book() {
		super();
		 
		this.category=Category.books;
	}

	 

	@Override
	public String toString() {
		return "Book [author=" + author + ", year=" + year + ", language=" + language + "]";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	
}
