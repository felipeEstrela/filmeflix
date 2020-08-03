package com.elo.filmeflix.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elo.filmeflix.exception.RecordNotFoundException;
import com.elo.filmeflix.model.MovieEntity;
import com.elo.filmeflix.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	MovieService service;

	@GetMapping
	public ResponseEntity<List<MovieEntity>> getAllMovies() {
		List<MovieEntity> list = service.getAllMovies();

		return new ResponseEntity<List<MovieEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieEntity> getMovieById(@PathVariable("id") Long id) throws RecordNotFoundException {
		MovieEntity entity = service.getMovieById(id);

		return new ResponseEntity<MovieEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MovieEntity> createOrUpdateMovie(@RequestBody MovieEntity movie)
			throws RecordNotFoundException {
		MovieEntity updated = service.createOrUpdateMovie(movie);
		return new ResponseEntity<MovieEntity>(updated, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@RequestMapping("/updateStatus")
	@PutMapping
	public ResponseEntity<MovieEntity> updateStatus(@RequestBody MovieEntity movie)
			throws RecordNotFoundException {
		MovieEntity updated = service.updateStatus(movie);
		return new ResponseEntity<MovieEntity>(updated, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MovieEntity> deleteMovieById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteMovieById(id);
		return new ResponseEntity<MovieEntity>(HttpStatus.OK);
	}

}