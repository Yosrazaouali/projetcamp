package com.example.demo.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Gender;
import com.example.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	  Optional<User> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
	  
	  @Query("Select COUNT(*) FROM User u where u.gender = :gender")
		int nbreByGenre(@Param("gender") Gender gender);
}
