package com.example.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
@JsonTypeName(value="movie")
@Type( value = Movie.class)
@Entity
@DiscriminatorValue("Movie")

public class Movie extends Product {

	private String director;
	private movieGenre genre;
	private int year;
	private String language;
	
	public Movie() {
		
	}
	

	public Movie(String title, double price, Category category, int amount,
			String director,movieGenre genre, int year,String language) {
		super(title, price, category, amount);
		this.director=director;
		this.genre=genre;
		this.year=year;
		this.language=language;
	}


	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public movieGenre getGenre() {
		return genre;
	}

	public void setGenre(movieGenre genre) {
		this.genre = genre;
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
