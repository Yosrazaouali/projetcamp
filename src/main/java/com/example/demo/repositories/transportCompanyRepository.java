package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.transportCompany;

@Repository
public interface transportCompanyRepository extends JpaRepository<transportCompany, Integer> {
	@Query("SELECT t.name, COUNT(r) FROM ReservationTransport r JOIN r.meansOfTransport m JOIN m.transportCompany t GROUP BY t")
    List<Object[]> countReservationsByTransportCompany();


    
}
