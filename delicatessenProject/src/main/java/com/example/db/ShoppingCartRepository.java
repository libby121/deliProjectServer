package com.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beans.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
