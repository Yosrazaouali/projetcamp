package com.example.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.entities.ReservationTransport;

public interface ReservationtransportService {
    void addReservationtransport(ReservationTransport ReservationTransport , Integer idU , Integer idM);
    void updateReservationtransport(Integer id, ReservationTransport ReservationTransport);
    void deleteReservationtransport(Integer id);
    List<ReservationTransport> getAll();
    ReservationTransport getById(Integer id);
    Integer nbrReservationTransport();
    void affecterMeansOfTransToRese(Integer idM , Integer idR);
    void affecterReservationToUser(Integer idU , Integer idR);
    ReservationTransport findReserByUsAnMean(Integer id ,Integer idTransport);
}

