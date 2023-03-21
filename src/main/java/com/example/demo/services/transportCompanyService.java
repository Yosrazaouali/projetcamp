package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.entities.transportCompany;

public interface transportCompanyService {
	void addTransportCompany(transportCompany transportCompany);

    void sendSmsvalide();

    void updateTransportCompany(Integer id, transportCompany transportCompany);

	void deleteTransportCompany(Integer id);

	List<transportCompany> getAll();

	transportCompany getById(Integer id);

	Integer nbrTransportCompany();

    List<Object[]> countReservationsByTransportCompany();



    

}
