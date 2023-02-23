package com.example.Tutorials.repository;

import java.util.List;
import java.util.Optional;
import com.example.Tutorials.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Tutorial_Repository extends JpaRepository<Tutorial, Long> {

	List<Tutorial> findAll();

	List<Tutorial> findByTitleContaining(String title);

	Optional<Tutorial> findById(long id);

	Tutorial save(Tutorial demo);

	void deleteById(long id);

	void deleteAll();

	List<Tutorial> findByPublished(boolean b);
	
	Optional<Tutorial> findByTitle(String title);
	

}
