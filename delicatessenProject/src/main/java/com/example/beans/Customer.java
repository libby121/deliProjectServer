package com.example.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customers")
public class Customer {

	public Customer(String firstName, String lastName, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private double revenue;
	@Column(name = "is_prime")
	private boolean isPrime;
	
//	@ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
//	private List<Product> products;
//	
	@OneToMany(mappedBy="customer")//the column from CustomersPurchases
	private Set<CustomerPurchase>purchases=new HashSet<CustomerPurchase>() ;
	
	 
	@JsonIgnore
	@OneToOne(  fetch = FetchType.EAGER)
	private ShoppingCart cart;

	public ShoppingCart getCart() {
		
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	

	public Customer() {

	}

	public Set<CustomerPurchase> getPurchases() {
  
		  return purchases;
	}

	public void setPurchases(Set<CustomerPurchase> purchases) {
		this.purchases = purchases;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public boolean isPrime() {
		return isPrime;
	}

	public void setPrime(boolean isPrime) {
		this.isPrime = isPrime;
	}

	public long getId() {
		return id;
	}

	 public List<Product>getMyProducts(){
	 
		 List<Product>purch=new ArrayList<Product>(); 
		for (CustomerPurchase customerPurchase : purchases) {
			if(customerPurchase.getCustomer().equals(this))
				purch.add(customerPurchase.getProduct());
		}return purch;
	 }

//	public List<Product> getProducts() {
//		return products;
//	}

}
