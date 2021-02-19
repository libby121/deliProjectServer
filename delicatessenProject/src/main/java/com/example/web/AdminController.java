package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.Book;
import com.example.beans.Category;
import com.example.beans.Customer;
import com.example.beans.Flower;
import com.example.beans.Food;
import com.example.beans.Movie;
import com.example.beans.Product;
import com.example.exceptions.IllegalProductPriceException;
import com.example.exceptions.categoryCannotBeChangedException;
import com.example.exceptions.customerDoesNotExistException;
import com.example.exceptions.customerExistsException;
import com.example.exceptions.expirationDateException;
import com.example.exceptions.noSuchBookExeption;
import com.example.exceptions.noSuchProductException;
import com.example.exceptions.productDuplicationException;
import com.example.facaeds.AdminFacade;

/**
 * @RestController-A special version(of spring 4) of @Controller which
 *                   simplifies the Restful Web services and combines
 *                   together @controller and @ResponseBody. restController
 *                   defines a servlet, the server actions. ResponseEntity (a
 *                   wrapper for the server http-response) is its default return
 *                   type. Default scope is Singleton.
 * 
 * @get,@post,@put @delete- the conventions for request type for each method.
 *                 the parenthesis that follows represent the virtual mapping of
 *                 the request, which must be unique (either by type or by
 *                 mapping path/url).
 * @post-the parameters sent in url are not visible, compared to @get.
 * @RequestMapping-Used for mapping web requests to specific path, on the
 *                      controller level.
 * 
 *                      query parameter- in order to get to the particular
 *                      method in the server we use " ? " after the url path and
 *                      then the name of the parameter and the sign " = " and
 *                      then the value . (8080/admin/one?name=dan). parameters
 *                      order has no importance.
 * 
 * @PathVariable-Used for method parameters which do not require the name of the
 *                    variable before them when sent to the server. the
 *                    parameter is passed in the url itself, without the name of
 *                    the parameter.(8080/admin/one/dan). parameters order is
 *                    crucial. As opposed to @QueryParam which are passed as a
 *                    key-value pair. This makes the URL shorter but in some
 *                    cases might be less straight-forward to use.
 * 
 * 
 * @CrossOrigin-By default requests that are sent from different origins i.e
 *                 different domains, ports, or protocols are being blocked by
 *                 the security mechanism of ajax- CORS policy. When adding this
 *                 spring annotation I can easily define which origin I do give
 *                 permission to.
 * 
 *
 * @RequestBody-Used for parameters that will not be sent in the URI itself but
 *                   in the request body data, will be found inside the page.
 *                   Especially for complex objects that are sent from client.
 * 
 * 
 *
 * @author ליבי
 *
 */

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {

	@Autowired
	private AdminFacade adminFac;

	@PostMapping("addProduct")
	public ResponseEntity<?> addProduct(@RequestBody Product p) {
		try {
			;
			return ResponseEntity.ok(adminFac.addProduct(p));
		} catch (productDuplicationException | expirationDateException | IllegalProductPriceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("products/OneBook/{pId}")
	public ResponseEntity<?>getOneBook(@PathVariable long pId){
		try {
			return ResponseEntity.ok(adminFac.getOneBook(pId));
		} catch (noSuchBookExeption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping("products/OneMovie/{pId}")
	public ResponseEntity<?>getOneMovie(@PathVariable long pId){
		try {
			return ResponseEntity.ok(adminFac.getOneMovie(pId));
		} catch (noSuchProductException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping("products/OneFood/{pId}")
	public ResponseEntity<?>getOneFood(@PathVariable long pId){
		try {
			return ResponseEntity.ok(adminFac.getOneFood(pId));
		} catch (noSuchProductException  e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@GetMapping("products/getOneFlower/{pId}")
	public ResponseEntity<?>getOneFlower(@PathVariable long pId){
		try {
			return ResponseEntity.ok(adminFac.getOneFlower(pId));
		} catch (noSuchProductException  e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PostMapping("addBook")
	public ResponseEntity<?>addBook(@RequestBody Book b){
		try {
			 
			return ResponseEntity.ok(adminFac.addBook(b));
		} catch (productDuplicationException | expirationDateException | IllegalProductPriceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("addMovie")
	public ResponseEntity<?>addMovie(@RequestBody Movie b){
		try {
			 
			return ResponseEntity.ok(adminFac.addMovie(b));
		} catch (productDuplicationException | expirationDateException | IllegalProductPriceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PostMapping("addFlower")
	public ResponseEntity<?>addBook(@RequestBody Flower b){
		try {
			 
			return ResponseEntity.ok(adminFac.addFlower(b));
		} catch (productDuplicationException | expirationDateException | IllegalProductPriceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PostMapping("addFood")
	public ResponseEntity<?>addFood(@RequestBody Food b){
		try {
			 
			return ResponseEntity.ok(adminFac.addFood(b));
		} catch (productDuplicationException | expirationDateException | IllegalProductPriceException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable long id) {/// ???
		try {
			adminFac.deleteProductById(id);
			return ResponseEntity.ok("product " + id + " deleted");
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("updateFlower")
	public ResponseEntity<?> updateFlower(@RequestBody Flower p)  {
	 try {
		adminFac.updateFlower(p);
		return ResponseEntity.ok(p); 
	} catch (categoryCannotBeChangedException | productDuplicationException
			| IllegalProductPriceException | expirationDateException | noSuchProductException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

	}
		
	}
	
	@PutMapping("updateBook")
	public ResponseEntity<?> updateProduct(@RequestBody Book p)   {
		try {
			adminFac.updateBook(p);
			return ResponseEntity.ok(p);

		} catch (noSuchBookExeption | categoryCannotBeChangedException | productDuplicationException
				| IllegalProductPriceException | expirationDateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
		
	}
	@PutMapping("updateMovie")
	public ResponseEntity<?> updateProduct(@RequestBody Movie p)  {
		try {
			adminFac.updateMovie(p);
			return ResponseEntity.ok(p);

		} catch (noSuchProductException | categoryCannotBeChangedException | productDuplicationException
				| IllegalProductPriceException | expirationDateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}
	@PutMapping("updateFood")
	public ResponseEntity<?> updateProduct(@RequestBody Food p) throws noSuchProductException, categoryCannotBeChangedException, productDuplicationException {
		 
			try {
				adminFac.updateFood(p);
				return ResponseEntity.ok(p);
			} catch (noSuchProductException | categoryCannotBeChangedException | productDuplicationException
					| IllegalProductPriceException | expirationDateException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());


			}
			
	 
	}
	
	@GetMapping("oneProduct/{id}")
	public ResponseEntity<?>getOneProduct(@PathVariable long id){
		try {
			return ResponseEntity.ok(adminFac.getProductById(id));
		}catch(noSuchProductException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@GetMapping("allProducts")
	public List<Product>getAllProducts(){
		return adminFac.getAllProducts();
	}
	
	@GetMapping("category/{c}")
	public List<Product>getProductsByCategory(@PathVariable Category c){
		return adminFac.getProductsByCategory(c);
	}
//	@GetMapping("one/{title}/{c}")
//	public ResponseEntity<?>getProductByTiTleAndCategory(@PathVariable String title, @PathVariable Category c){
//	try {	
//		return ResponseEntity.ok(adminFac.getProductByTitleAndCategory(title, c));
//	}catch(noSuchProductException e) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//	}
	
	//}
	@PostMapping("addCustomer")
	public ResponseEntity<?>addCustomer(@RequestBody Customer c){
		try {
			return ResponseEntity.ok(adminFac.addCustomer(c));
		}catch(customerExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	

	@GetMapping("oneCustomer")
	public ResponseEntity<?>getOneCustomer(long id){
		try {
			return ResponseEntity.ok(adminFac.getCustomerById(id));
		}catch(customerDoesNotExistException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping("oneCustomer/{email}")
	public ResponseEntity<?>getOneCustomer(@PathVariable String email){
		try {
			return ResponseEntity.ok(adminFac.getCustomerByEmail(email));
		}catch(customerDoesNotExistException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("OneCustomer/name/{name}")
	public List<Customer>getCustomerByName(String name){
		return adminFac.getCustomersByName(name);
	}
	
	@GetMapping("OneCustomer/lname/{name}")
	public List<Customer>getCustomerByLastName(String lname){
		return adminFac.getCustomersByLastName(lname);
	}
	
	@GetMapping("allCustomers")
	public List<Customer>getCustomers(){
		return adminFac.getAllCustomers();
	}
	
	
	@GetMapping("primeCustomers")
	public List<Customer>getPrimeCustomers(){
		return adminFac.getCustomersPrime();
	}
	
	@DeleteMapping("deleteCustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
		try {
			adminFac.deleteCustomer(id);
			return ResponseEntity.ok("customer "+id+" deleted");
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("no customer by that id");
		}
	}
	
	
	
	@GetMapping("allFoods")
	public List<Food>getfoods(){
		return adminFac.getAllFoods();
	}
	
	
	

}
