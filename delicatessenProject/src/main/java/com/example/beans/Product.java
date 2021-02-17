package com.example.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Parent;
import org.springframework.expression.BeanResolver;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
//override hashcode+equals!

/**
 * a table for the general fields of a product(this class fields) will be
 * connected by the product_id to all the categories tables(its java
 * sub-classes).
 * 
 * @author ליבי
 *
 */

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class"
//
//)
 
//, @JsonSubTypes.Type(value = Flower.class),
//		@JsonSubTypes.Type(value = Food.class), @JsonSubTypes.Type(value = Movie.class), })
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)//??? 
//i cannot add a "product" since its an abstract class.
//but i can delete, and do all the fetching from the different tables by the automatic join 

//@Inheritance(strategy = InheritanceType.JOINED)// i do not manage to do that. my purpose was a separate table + 
//super class table. i managaed to do only the table per class, other wise i get issues with sql.

@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "category")

public abstract class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
 	@Column(name = "product_id")
	protected long id;
	 
	protected String title;
	 
	protected double price;
	//when i do not add transient i get sql exception..
	//@Transient//javax=> ignores the field in sql.
	//it does appear in the super table though, but cannot be fetched....
	@Column(insertable = true, updatable = true )
	 @Enumerated(EnumType.STRING)
	 
	protected Category category;
	 
	protected String description;
	 
	protected int amount;
	 
	protected String image;
 

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}



	public Product() {
		 
	 
		 
	}


 


	@OneToMany(mappedBy = "product") // the corresponding column name from CustomersPurchases
	private Set<CustomerPurchase> purchases;

//	@JoinTable(name="purchases", joinColumns = @JoinColumn(name = "product_id"), 
//			inverseJoinColumns = @JoinColumn(name = "customer_id"))	
//	@ManyToMany(fetch=FetchType.EAGER)
//	private List<Customer>customers;

//	public List<Customer> getCustomers() {
//		return customers;
//	}

	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
	private List<ProductShoppingCart> productCarts;

	public Product(String title, double price, Category category, int amount) {
		super();
		this.title = title;
		this.price = price;
		this.category = category;
		this.amount = amount;

	}
	
	

//	public Product(String title, double price, Category category, String description, int amount, String image) {
//		super();
//		this.title = title;
//		this.price = price;
//		this.category = category;
//		this.description = description;
//		this.amount = amount;
//		this.image = image;
//	}

	public Set<CustomerPurchase> getPurchases() {

		return purchases;
	}

	public void setPurchases(Set<CustomerPurchase> purchases) {
		this.purchases = purchases;
	}

	public List<ProductShoppingCart> getProductCarts() {
		return productCarts;
	}

	public void setProductCarts(List<ProductShoppingCart> productCarts) {
		this.productCarts = productCarts;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Customer> myCustomers() {
		List<Customer> custs = new ArrayList<Customer>();
		for (CustomerPurchase customerPurchase : purchases) {
			if (customerPurchase.getProduct().equals(this))
				custs.add(customerPurchase.getCustomer());
		}
		return custs;
	}
	

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category
				+ ", description=" + description + ", amount=" + amount + ", image=" + image + "]";
	}

}
