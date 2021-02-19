package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.ShoppingCart;
import com.example.exceptions.customerNotFoundException;
import com.example.exceptions.productNotFoundException;
import com.example.facaeds.CustomerFacade;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	
	@Autowired
	private CustomerFacade custFac;
	
	
	@GetMapping("cart")
	public ResponseEntity<?> getOrCreateCart() {
		try {
			return ResponseEntity.ok(this.custFac.GetOrProvideCart());
		}catch(customerNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("addToCart/{productId}")
	public ResponseEntity<?>addProductToCart(@PathVariable long productId){
		 
			try {
				this.custFac.addToCart(productId);
				return ResponseEntity.ok(productId+" added to cart!");

			} catch (customerNotFoundException | productNotFoundException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
	 
	}
	
	
	
}
