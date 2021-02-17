package com.example.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beans.Flower;

public interface FlowerRepository extends JpaRepository<Flower,Long> {

	Optional<Flower> getById(long id);
}
