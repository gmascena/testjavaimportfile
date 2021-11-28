package com.texoit.teste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.texoit.teste.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByWinner(String winner);

}
