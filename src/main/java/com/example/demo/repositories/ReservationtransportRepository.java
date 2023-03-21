package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ReservationTransport;

@Repository
public interface ReservationtransportRepository extends JpaRepository<ReservationTransport, Integer>{
	@Query("SELECT r FROM ReservationTransport r Where r.user.id=:id AND r.meansOfTransport.idTransport=:idTransport")
	ReservationTransport findReserByUsAnMean(@Param("id") Integer id , @Param("idTransport") Integer idTransport);

	List<ReservationTransport> findByUserUsername(String username);
}
