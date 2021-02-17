package com.example.beans;

import java.io.Serializable;

/*
 * A class that defines the primary key which is composed of two fields.
 *  will not be mapped to a table. 
 *  cart and item that create the p.k are referenced to here from ProductShoppingCart
 *   entity which uses that composed p.k. using @ManyToOne

 */
public class ProductCartId implements Serializable {

	private ShoppingCart cart;
	private Product item;
}
