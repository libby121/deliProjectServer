package com.example;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.beans.Book;
import com.example.beans.Category;
import com.example.beans.Flower;
import com.example.beans.Product;
import com.example.beans.bouquetColor;
import com.example.beans.bouquetSize;
import com.example.beans.bouquetType;

@SpringBootApplication
public class DelicatessenProjectApplication {

	// Product p= new Book("fontanela", 97, Category.books, 25, "meir shalev", 2007,
	// "heb");

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DelicatessenProjectApplication.class, args);
//		try {
//			RestTemplate request = new RestTemplate();
//			Flower b = new Flower("yy2",89,Category.flowers,789,bouquetSize.large,bouquetColor.colorful,
//					bouquetType.iris);
//			//Book bb = new Book( );
//			ResponseEntity<Flower> p = request.postForEntity("http://localhost:8080/admin/addFlower", b,
//					Flower.class);
//			System.out.println(p.getBody());
//		} catch ( HttpClientErrorException e) {
//			System.out.println(e.getMessage());
//		 
//		}
		
		 
	}}
