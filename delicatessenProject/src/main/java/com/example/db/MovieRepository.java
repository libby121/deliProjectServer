package com.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beans.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
