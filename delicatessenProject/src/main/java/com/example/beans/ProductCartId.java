package com.example.beans;

import java.io.Serializable;

/*
 * A class that defines the primary key which is composed of two fields.
 *  will not be mapped to a table. 
 *  cart and item that create the p.k are referenced to here from ProductShoppingCart
 *   entity which uses that composed p.k. using @ManyToOne

 */
public class ProductCartId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ShoppingCart cart;
	private Product item;
	
	public ProductCartId(ShoppingCart cart, Product item) {
		super();
		this.cart = cart;
		this.item = item;
	}
	public ProductCartId() {
		super();
	}
	public ShoppingCart getCart() {
		return cart;
	}
	 
	public Product getItem() {
		return item;
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCartId other = (ProductCartId) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
	
	
}
