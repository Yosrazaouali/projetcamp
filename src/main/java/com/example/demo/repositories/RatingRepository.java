package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Rating;
import com.example.demo.entities.meansOfTransport;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{

	List<Rating> findByMeansOftransport(meansOfTransport transport);
	
	 @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.meansOftransport.id = :id")
	  Double getMeanRatingByMeansOfTransportId(@Param("id") Integer id);

}
