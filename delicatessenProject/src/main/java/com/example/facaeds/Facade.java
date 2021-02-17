package com.example.facaeds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.beans.Category;
import com.example.beans.Product;
import com.example.db.AdministratorRepository;
import com.example.db.BookRepo;
import com.example.db.CustomerRepository;
import com.example.db.FlowerRepository;
import com.example.db.FoodRepository;
import com.example.db.MovieRepository;
import com.example.db.ProductRepository;
import com.example.db.ShoppingCartRepository;
import com.example.exceptions.customerNotFoundException;
import com.example.exceptions.noSuchProductException;

public abstract class Facade {

	@Autowired
	protected AdministratorRepository adminRepo;
	@Autowired
	protected CustomerRepository customerRepo;
	@Autowired
	protected ProductRepository productRepo;
	@Autowired
	protected ShoppingCartRepository cartRepo;
	@Autowired
	protected BookRepo bookRepo;
	@Autowired
	protected MovieRepository movieRepo;
	@Autowired
	protected FlowerRepository flowerRepo;
	@Autowired
	protected FoodRepository foodRepo;

	protected abstract boolean login(String email, String password) throws customerNotFoundException;

	public List<Product> getAllProducts() {
		return productRepo.findAll();
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
}
