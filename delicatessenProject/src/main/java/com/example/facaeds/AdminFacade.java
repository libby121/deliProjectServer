package com.example.facaeds;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.beans.Admin;
import com.example.beans.Book;
import com.example.beans.Category;
import com.example.beans.Customer;
import com.example.beans.Flower;
import com.example.beans.Food;
import com.example.beans.Movie;
import com.example.beans.Product;
import com.example.db.BookRepo;
import com.example.exceptions.IllegalProductPriceException;
import com.example.exceptions.categoryCannotBeChangedException;
import com.example.exceptions.customerDoesNotExistException;
import com.example.exceptions.customerExistsException;
import com.example.exceptions.expirationDateException;
import com.example.exceptions.noSuchBookExeption;
import com.example.exceptions.noSuchProductException;
import com.example.exceptions.productDuplicationException;

import javassist.expr.Instanceof;

/**
 * 
 * @Service is a specific @component(=>signals classes to be managed by spring
 *          context with the functionality of dependency injection.)
 * @service marks for spring a facade class, used for defining the business
 *          logic. Since each connected user will get his own facade eventually,
 *          and both administrator and customer facades hold a variable of
 *          identification,of which the returned facade is dependent upon, the
 *          service scope is set to prototype. So that when a user gets a facade
 *          after the login, he gets an object that contains his individual
 *          possible functionalities.
 */

@Service
@Scope(value = "prototype")
public class AdminFacade extends Facade {
	/*
	 * addProduct, delete, update, get one, get all, get by category
	 * addCustomer.delete,update, get one, get all, get prime, get by mail
	 * 
	 */
	private long adminId;

	public boolean login(String email, String password) {
		Admin admin = adminRepo.findByEmailAndPassword(email, password);
		if (admin != null) {
			this.adminId = admin.getId();
			return true;
		}
		return false;

	}

	public Book getOneBook(long id) throws noSuchBookExeption {
		return bookRepo.findById(id).orElseThrow(noSuchBookExeption::new);
	}
	
	public Food getOneFood(long id) throws  noSuchProductException {
		return foodRepo.findById(id).orElseThrow(noSuchProductException::new);
	}
	
	public Flower getOneFlower(long id) throws noSuchProductException {
		return flowerRepo.getById(id).orElseThrow(noSuchProductException::new);
	}
	
	public Movie getOneMovie(long id) throws noSuchProductException {
		return movieRepo.findById(id).orElseThrow(noSuchProductException::new);
	}
	
	private boolean validProduct(Product p)
			throws productDuplicationException, IllegalProductPriceException, expirationDateException {
//		for (Product prod : productRepo.findAll()) {
//			if (prod.getTitle().equals(p.getTitle()) && (prod.getCategory().equals(p.getCategory())))
//				throw new productDuplicationException();
//		}
		if (p.getPrice() <= 0)
			throw new IllegalProductPriceException();
		if (p instanceof Food) {
			Calendar cal = Calendar.getInstance();

//			if (((Food) p).getExpirationDate().after(cal.getTime()))
//				throw new expirationDateException();
	}
		return true;
	}

	//when i save to the sub class table it automatically add the product to the super table!!
	public Movie addMovie(Movie m)
			throws productDuplicationException, IllegalProductPriceException, expirationDateException {

		if (validProduct(m))
			movieRepo.save(m);

		return m;
	}

	public Flower addFlower(Flower f) throws productDuplicationException, IllegalProductPriceException, expirationDateException {


		if (validProduct(f))
			flowerRepo.save(f);

		return f;
	}


	public Food addFood(Food f) throws productDuplicationException, IllegalProductPriceException, expirationDateException {


		if (validProduct(f))
			foodRepo.save(f);

		return f;
	}

	public Book addBook(Book b)
			throws productDuplicationException, IllegalProductPriceException, expirationDateException {



		if (validProduct(b))
			bookRepo.save(b);

		return b;
	}

		public Product addProduct(Product p)// how can i use only this method as polymorphic?
				throws productDuplicationException, expirationDateException, IllegalProductPriceException {
	
			switch(p.getCategory()) {
			case books:
				Book b=(Book)p;
				System.out.println(b.getAuthor());
				return addBook(b);
			}
			return null;
			//p.setId(0);// here or in controller, validation for client
	
			//productRepo.addProduct(p.getId());
			//productRepo.save(p);
			//return p;
	
		}

	public void deleteProductById(long id) {// what should be checked before deletion?
		if (!(productRepo.findById(id).isPresent())) {System.out.println("no such pro");//decide what to do here
			return;}
		productRepo.deleteById(id);

	}
	
	public void updateBook(Book b) throws noSuchBookExeption, categoryCannotBeChangedException, productDuplicationException, IllegalProductPriceException, expirationDateException {
		Book fetched=bookRepo.findById(b.getId()).orElseThrow(noSuchBookExeption::new);
		if(!fetched.getCategory().equals(b.getCategory()))throw new categoryCannotBeChangedException();
		if(validProduct(b))bookRepo.save(b);
	}
	public void updateFlower(Flower f) throws noSuchProductException, categoryCannotBeChangedException, productDuplicationException, IllegalProductPriceException, expirationDateException {
		Flower fetched=flowerRepo.findById(f.getId()).orElseThrow(noSuchProductException::new);
		if(!fetched.getCategory().equals(f.getCategory()))throw new categoryCannotBeChangedException();
		if(validProduct(f))flowerRepo.save(f);
	}
	public void updateMovie(Movie m) throws noSuchProductException, categoryCannotBeChangedException, productDuplicationException, IllegalProductPriceException, expirationDateException {
		Movie fetched=movieRepo.findById(m.getId()).orElseThrow(noSuchProductException::new);
		if(!fetched.getCategory().equals(m.getCategory()))throw new categoryCannotBeChangedException();
		if(validProduct(m))movieRepo.save(m);
	}
	public void updateFood(Food f) throws noSuchProductException, categoryCannotBeChangedException, productDuplicationException, IllegalProductPriceException, expirationDateException {
		Food fetched=foodRepo.findById(f.getId()).orElseThrow(noSuchProductException::new);
		if(!fetched.getCategory().equals(f.getCategory()))throw new categoryCannotBeChangedException();
		if(validProduct(f))foodRepo.save(f);
	}
	

	public void updateProduct(Product p)//how can this be used ?
			throws productDuplicationException, expirationDateException, IllegalProductPriceException, categoryCannotBeChangedException, noSuchProductException {
	
		Product fetched=productRepo.findById(p.getId()).orElseThrow(noSuchProductException::new);
		if(!fetched.getCategory().equals(p.getCategory()))throw new categoryCannotBeChangedException();
		if(validProduct(p))productRepo.save(p);

		productRepo.save(p);
	}

	public Product getProductById(long id) throws noSuchProductException {
		return productRepo.findById(id).orElseThrow(noSuchProductException::new);
	}

		public Product getProductByTitleAndCategory(String title, Category category) throws noSuchProductException {
			return productRepo.getByTitleAndCategory(category, title).orElseThrow(noSuchProductException::new);
	
		}
	
		public List<Product> getProductsByCategory(Category c) {
			return productRepo.getByCategory(c);
		}

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public Customer addCustomer(Customer c) throws customerExistsException {
		if (customerRepo.existsByEmail(c.getEmail()))
			throw new customerExistsException();
		return customerRepo.save(c);
	}

	public void updateCustomer(Customer c) {// check if it does not add instead of updating. maybe will have to add id
		// to customer
		customerRepo.save(c);
	}

	public Customer getCustomerById(long id) throws customerDoesNotExistException {
		return customerRepo.findById(id).orElseThrow(customerDoesNotExistException::new);
	}

	public Customer getCustomerByEmail(String email) throws customerDoesNotExistException {
		return customerRepo.findByEmail(email).orElseThrow(customerDoesNotExistException::new);
	}

	public List<Customer> getCustomersByName(String name) {
		return customerRepo.getByFirstName(name);

	}

	public List<Customer> getCustomersByLastName(String lname) {
		return customerRepo.getByLastName(lname);

	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public List<Customer> getCustomersPrime() {
		return customerRepo.findByPrimeTrueNative();
	}

	public void deleteCustomer(long id) {
		customerRepo.deleteById(id);
	}

}
