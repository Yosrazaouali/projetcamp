package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.meansOfTransport;
import com.example.demo.entities.transportCompany;

@Repository
public interface meanOfTransportRepository extends JpaRepository<meansOfTransport, Integer> {
	 @Query("SELECT DISTINCT r.meansOftransport.transportCompany FROM Rating r WHERE r.meansOftransport.idTransport IN "
	         + "(SELECT r.meansOftransport.idTransport FROM Rating r GROUP BY r.meansOftransport.idTransport HAVING AVG(r.rating) = :rating)")
	  List<transportCompany> getTransportCompaniesByMeansOfTransportRating(@Param("rating") Double rating);
	  
	 
}
