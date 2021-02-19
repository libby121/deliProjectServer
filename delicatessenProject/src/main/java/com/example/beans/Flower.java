package com.example.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
//@JsonTypeName(value="flower")
 @Entity
@DiscriminatorValue("flowers")

public class Flower extends Product {

	private bouquetSize size;
	private bouquetColor color;
	private bouquetType type;
	
	public Flower() {
		
	}

	public Flower(String title, double price, Category category, int amount,
			String description,bouquetSize size,
			bouquetColor color, bouquetType type) {
		super(title, price, category, amount,description);
		this.size = size;
		this.color = color;
		this.type = type;	}

	public Flower(bouquetSize size, bouquetColor color, bouquetType type) {
		super();
		this.size = size;
		this.color = color;
		this.type = type;
	}

	public bouquetSize getSize() {
		return size;
	}

	public void setSize(bouquetSize size) {
		this.size = size;
	}

	public bouquetColor getColor() {
		return color;
	}

	public void setColor(bouquetColor color) {
		this.color = color;
	}

	public bouquetType getType() {
		return type;
	}

	public void setType(bouquetType type) {
		this.type = type;
	}
	
	
	
}
