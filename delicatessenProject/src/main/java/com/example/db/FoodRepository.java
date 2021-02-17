package com.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beans.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
