package com.example.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Instead of automatically creating a manyToMany table for the customers purchases
 * (with @manyToMany annotation) I create it myself, this will enable me to add additional
 * fields to that table.
 * Steps:
 * 1. add this class- to be mapped to the join table(customers_purhcases).
 * 2. instead of using manyToMany in the two other entities(each connected to the other),
 * 	 oneToMany is used on each of them, and is referred to the join table.
 * 
 * @author ליבי
 *
 */

@Entity
@Table(name="customers_purchases")
public class CustomerPurchase {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="purchase_id")
	private long id;
	
	@Column(name="purchase_date")
	private Date purchaseDate;//later ill be able to fetch all of the purchases of the same product
	

	@ManyToOne
	/**
	 *  @joinColumn indicates that this entity is the owner of the relationship (that is: the corresponding
	 *   table has a column with a foreign key to the referenced table), whereas the attribute 
	 *   mappedBy indicates that the entity in this side is the inverse of the relationship,
	 *   and the owner resides in the "other" entity.
	 */
	@JoinColumn(name="customer_identity")//Necessary?
	private Customer customer;
	
	@ManyToOne
	private Product product;

	
	
	
	public CustomerPurchase(Customer customer, Product product) {
		super();
		this.customer = customer;
		this.product = product;
	}

	public CustomerPurchase() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	
	
	
	
	
	
	
}
