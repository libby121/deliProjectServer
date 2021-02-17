package com.example.beans;

import java.util.List;

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

@Entity
@Table(name="carts_customers")
public class ShoppingCart {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private long id;
	@OneToOne(mappedBy="cart")
	private Customer customer;
	/**
	 * each customer cart is attached to a productShoppingCart object,
	 * instead of being attached to a list of products.
	 * because each product can be bought several times
	 */
	@OneToMany(fetch=FetchType.EAGER, mappedBy="cart")
	private List<ProductShoppingCart>productsInCart;

	public ShoppingCart(Customer c) {
		this.customer=c;
	}
	
	public ShoppingCart() {
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<ProductShoppingCart> getProductsOfCart() {
		return productsInCart;
	}

	public void setProductCarts(List<ProductShoppingCart> productCarts) {
		this.productsInCart = productCarts;
	}

	public long getId() {
		return id;
	}
	
	
	 
}
