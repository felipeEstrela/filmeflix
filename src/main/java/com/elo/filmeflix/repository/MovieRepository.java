package com.elo.filmeflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elo.filmeflix.model.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
	
	@Query("SELECT m FROM MovieEntity m WHERE m.active = true ORDER BY m.id DESC")
	public List<MovieEntity> findAllActive();
}