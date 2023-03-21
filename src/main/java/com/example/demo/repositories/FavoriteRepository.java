package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	List<Favorite> findByUserId(Integer userId);
	Favorite findByUserIdAndMeanOfTransportId(Integer userId, Integer meanOfTransportId);

}
