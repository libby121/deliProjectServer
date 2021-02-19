package com.example.beans;

import java.sql.Date;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
@Type( value = Food.class)
@JsonTypeName(value="food")

@Entity
@DiscriminatorValue("Food")
 
public class Food extends Product{

	
	private FoodCategory foodType;
	private Date expirationDate;//sql date object
	
	
	public Food() {
 	}


	public Food(String title, double price, Category category, int amount,
			String description,FoodCategory foodType, Date expirationDate) {
		super(title, price, category, amount,description);
		this.foodType = foodType;
		this.expirationDate = expirationDate;
	}


	public FoodCategory getFoodType() {
		return foodType;
	}


	public void setFoodType(FoodCategory foodType) {
		this.foodType = foodType;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
}
