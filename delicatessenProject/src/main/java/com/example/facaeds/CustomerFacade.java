package com.example.facaeds;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.beans.Category;
import com.example.beans.Customer;
import com.example.beans.CustomerPurchase;
 import com.example.beans.Product;
import com.example.beans.ProductShoppingCart;
import com.example.beans.ShoppingCart;
import com.example.db.CustomerRepository;
import com.example.db.ShoppingCartRepository;
import com.example.exceptions.customerNotFoundException;
import com.example.exceptions.noSuchProductException;
import com.example.exceptions.nosuchCartException;
import com.example.exceptions.productNotFoundException;
/**
 * 
 * @Service is a specific @component(=>signals classes to be managed by spring
 *          context with the functionality of dependency injection.)
 * @service marks for spring a facade class, used for defining the business
 *          logic. Since each connected user will get his own facade eventually,
 *          and both administrator and customer facades hold a variable of
 *          identification,of which the returned facade is dependent upon, the
 *          service scope is set to prototype. So that when a user gets a facade
 *          after the login, he gets an object that contains his individual possible
 *          functionalities.
 */

@Service
@Scope(value="prototype")
public class CustomerFacade extends Facade {

	private long customerId;
	private long cartId;

	@Override
	public boolean login(String email, String password) throws customerNotFoundException {
		Customer c = customerRepo.findByEmailAndPassword(email, password);
		if (c != null) {
			this.customerId = c.getId();
			return true;
		}
		throw new customerNotFoundException();

	}
	/*
	 * purchase, get all, get one, add to cart, cancel purchase,get by
	 * category/price, delete from cart
	 */


	/**
	 * delete product from cart:
	 * 1. fetch the customer cart, by the cart id.
	 * 
	 * 2. delete the data of the cart from the carts-customers table.
	 * 3. update the db layer.
	 * @param id
	 * @throws nosuchCartException 
	 * @throws noSuchProductException 
	 */
	public void deleteProductFromCart(long prodId) throws nosuchCartException, noSuchProductException {
		ShoppingCart cart= cartRepo.findById(cartId).orElseThrow(nosuchCartException :: new);
		Product p=productRepo.findById(prodId).orElseThrow(noSuchProductException:: new);
 	
 		//delete the product (instead of cart.getProducts() which is not possible)
		List<ProductShoppingCart>cartProductsWrapper=cart.getProductsOfCart();
		 ProductShoppingCart itemWrraper=null;
		for (ProductShoppingCart productShoppingCart : cartProductsWrapper) {
		if(	productShoppingCart.getItem().equals(p))itemWrraper=productShoppingCart;
		}itemWrraper.setItem(null);
		
		cartRepo.save(cart);
	}
	
	
	public ShoppingCart GetOrProvideCart() throws customerNotFoundException {
		//if(!(customerRepo.existsById(customerId)))throw new customerNotFoundException();
		Customer c=customerRepo.findById(customerId).orElseThrow(customerNotFoundException:: new);
		if(c.getCart()!=null) {this.cartId=c.getCart().getId();return c.getCart();}
		ShoppingCart cart=new ShoppingCart(c);
		cartRepo.save(cart);
		cartId=cart.getId();
		return cart;
	
	}
	/*
	 * purchase a product:
	 *  1. verify the customer 
	 *  2. verify the product
	 *  3. verify that the product amount is not 0 
	 *  4. can the coupon be expired?? i delete the expired ones in the thread..
	 *  5. delete 1 from the quantity of the product in the cart. (purchasing means cart exists)
	 *  6. delete 1 from the quantity of the product in general.
	 *  7. add the product to the purchases table(which is a list)
	 */
	public void purchaseProduct(long prodid) throws customerNotFoundException, productNotFoundException {
		 if(!(customerRepo.existsById(customerId)))throw new
		 customerNotFoundException();
		Customer customer = customerRepo.findById(customerId).orElseThrow(customerNotFoundException::new);// needed the
		Product p = productRepo.findById(prodid).orElseThrow(productNotFoundException::new);
		ShoppingCart cart=GetOrProvideCart();
		for (ProductShoppingCart prod : cart.getProductsOfCart()) {
			if(prod.getItem().equals(p)) {//found the item in cart
				prod.setQuantity(prod.getQuantity()-1);
				if(prod.getQuantity()==0)prod.setItem(null);
				cartRepo.save(cart);
			}
		}
		//customer.getProducts().add(p);customerRepo.save(customer);=> check if thats the same
		p.setAmount(p.getAmount()-1);
		
		//????
		CustomerPurchase newPurchase=new CustomerPurchase(customer,p);
		p.getPurchases().add(newPurchase);//check what happens when its set
		productRepo.save(p);//these lines will automatically add the purchase line to the
		//manyToMany table of purchases/customers

	}
	
	
	/*
	 * cancel order: 
	 * 1. fetch the customer and the product
	 * 2. add 1 to the quantity of that product in customer cart/add the product to the cart 
	 * 3. delete the product from customer's product list
	 * 4. add 1 to the general amount of the product
	 */
	
	public void cancelPurchase(long productId) throws customerNotFoundException, noSuchProductException {
		Customer c=customerRepo.findById(customerId).orElseThrow(customerNotFoundException:: new);
		Product p=productRepo.findById(productId).orElseThrow(noSuchProductException:: new);
		boolean isTheProductInCart=false;
		for (ProductShoppingCart ca :c.getCart().getProductsOfCart()) {
			if(ca.getItem().equals(p)) {
				ca.setQuantity(ca.getQuantity()+1);
				isTheProductInCart=true;
			}
		}if(!isTheProductInCart) { 
			ShoppingCart cart=GetOrProvideCart();
			ProductShoppingCart newProductToCart=new ProductShoppingCart(cart,p);
			c.getCart().getProductsOfCart().add(newProductToCart);
						
				}
				//c.getProducts().remove(p);
			c.getMyProducts().remove(p);//???????
			 
				//c.getPurchases()
				p.setAmount(p.getAmount()+1);
				customerRepo.save(c);
				productRepo.save(p);
			}
			
//			public List<Product> getAllProductsByCategory(Category c){
//			return productRepo.getByCategory(c);
//			 }
//			
//			public List<Product>getAllProductsByPrice(double p){
//				return productRepo.getByPrice(p);
//			}
			
	
	
	
	
	
	
	
	
	
	

}
