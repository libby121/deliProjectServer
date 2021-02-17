package com.example.db;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.beans.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
	
 
	

}
