package com.example.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

 
	boolean existsById(long id);
	boolean existsByEmailAndPassword(String email, String password);
	 Customer findByEmailAndPassword(String email, String password);
	 @Query(nativeQuery = true, value="insert into purchases "
	 		+ "values ( productId, customerId)")
	 void purchaseProduct(long customerId, long productId);
	 
	boolean existsByEmail(String email);
	
	Optional<Customer> findByEmail(String email);
	
	List<Customer>getByFirstName(String name);
	List<Customer>getByLastName(String name);
	
	//List<Customer>findByPrimeTrue();
	
	@Query(value="SELECT * FROM customers WHERE is_prime IS true", nativeQuery=true)
	List<Customer>findByPrimeTrueNative();

	
	 
}
