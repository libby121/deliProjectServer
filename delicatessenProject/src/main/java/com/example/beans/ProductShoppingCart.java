package com.example.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "items_carts")
/*
 * A kind of wrapper class for the product. Will be mapped to a manyToMany
 * table-> cart | item |item quantity IdClass- of which class the primary key of
 * the table is taken. cart + item create the p.k ,instead of having only the
 * cart as p.k, that way there won't be two lines of the same product and same
 * cart, but one line for each couple, in which the item quantity gets updated.
 * when using @Idclass i also need to add the two fields of which the p.k is
 * composed of.
 */
//@IdClass(ProductCartId.class)=>did not work
public class ProductShoppingCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Column(name="record_id")
	@Id // ? is is Idclass needed??
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 @JsonIgnore
	 @ManyToOne
	private ShoppingCart cart;
	// @Id
 	@ManyToOne
//	@ManyToOne(
//	        fetch = FetchType.LAZY)
//	    @NotFound(
//	        action = NotFoundAction.IGNORE)
	private Product item;

	@Override
	public String toString() {
		return "ProductShoppingCart [item=" + item + ", quantity=" + quantity + "]";
	}

	private int quantity;

	public ProductShoppingCart(ShoppingCart carta, Product item) {
		this.cart = carta;
		this.item = item;
		this.quantity = 1;

	}

	public ProductShoppingCart() {
		super();
	}

	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item = item;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {

		this.quantity = quantity;

	}

}
