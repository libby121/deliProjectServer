package com.example.db;

import java.util.List;
import java.util.Optional;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.beans.Category;
import com.example.beans.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value="SELECT * FROM products WHERE category=:#{#category.ordinal()} AND title=:t")
	  Optional<Product> getByTitleAndCategory(Category c,String t) ;

	
	//@Query("select c from Product c where c.category=:c")
		List<Product>getByCategory(Category c);
		
		List<Product>getByPrice(double price);
//		
//		@Transactional
//		@Modifying
//		@Query(value="insert into products (product_id, category) values(:id)", nativeQuery = true)
//		void addProduct( @Param("id")long id);
		
	 
}
