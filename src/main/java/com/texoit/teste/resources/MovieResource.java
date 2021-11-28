package com.texoit.teste.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.teste.entities.Movie;
import com.texoit.teste.entities.Result;
import com.texoit.teste.services.MovieService;

@RestController
@RequestMapping(value="/movies")
public class MovieResource {
	
	@Autowired
	private MovieService service;

	@GetMapping
	public ResponseEntity<List<Movie>> findAll(){
		List<Movie> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/1")
	public ResponseEntity<List<Result>> getRequirements(){
		List<Result> list = service.getRequirements();
		return ResponseEntity.ok().body(list);
	}

}
