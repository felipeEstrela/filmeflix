package com.elo.filmeflix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elo.filmeflix.exception.RecordNotFoundException;
import com.elo.filmeflix.model.MovieEntity;
import com.elo.filmeflix.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository repository;
	
	public List<MovieEntity> getAllMovies() {
		List<MovieEntity> movieList = repository.findAllActive();
		
		if (movieList.size() > 0) {
			return movieList;
		} else {
			return new ArrayList<MovieEntity>();
		}
	}

	public MovieEntity getMovieById(Long id) throws RecordNotFoundException {
		Optional<MovieEntity> movie = repository.findById(id);

		if (movie.isPresent()) {
			return movie.get();
		} else {
			throw new RecordNotFoundException("No movie record exist for given id");
		}
	}

	public MovieEntity createOrUpdateMovie(MovieEntity entity) throws RecordNotFoundException {

		Optional<MovieEntity> movie;

		if (entity.getId() != null) {
			movie = repository.findById(entity.getId());
			if (movie.isPresent()) {
				MovieEntity newEntity = movie.get();
				newEntity.setTitle(entity.getTitle());
				newEntity.setYearRelease(entity.getYearRelease());
				newEntity.setDescription(entity.getDescription());
				newEntity.setGenre(entity.getGenre());
				newEntity.setActive(entity.getActive());
				newEntity = repository.save(newEntity);

				return newEntity;
			}
		}

		entity = repository.save(entity);
		return entity;
	}

	public MovieEntity updateStatus(MovieEntity entity) throws RecordNotFoundException {
		
		Optional<MovieEntity> movie;
		
		movie = repository.findById(entity.getId());
		
		if (movie.isPresent()) {
			MovieEntity newEntity = movie.get();
			
			newEntity.setTitle(movie.get().getTitle());
			newEntity.setYearRelease(movie.get().getYearRelease());
			newEntity.setDescription(movie.get().getDescription());
			newEntity.setGenre(movie.get().getGenre());
			newEntity.setActive(entity.getActive());
			
			newEntity = repository.save(newEntity);
			
			return newEntity;
		}
		
		entity = repository.save(entity);
		return entity;
	}

	public void deleteMovieById(Long id) throws RecordNotFoundException {
		Optional<MovieEntity> movie = repository.findById(id);

		if (movie.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No movie record exist for given id");
		}
	}
}