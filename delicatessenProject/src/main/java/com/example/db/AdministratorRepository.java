package com.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beans.Admin;

public interface AdministratorRepository extends JpaRepository<Admin, Long> {

	
	boolean existsByEmailAndPassword(String email, String password);
	
	Admin findByEmailAndPassword(String email, String passsword);
}
