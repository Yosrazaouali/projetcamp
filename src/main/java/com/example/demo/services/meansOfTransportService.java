package com.example.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.entities.meansOfTransport;
import com.example.demo.entities.transportCompany;

public interface meansOfTransportService {
	void addmeanOfTransport(meansOfTransport meanOftransport);

	void updatemeanOfTransport(Integer id, meansOfTransport meanOfTransport);

	void deletemeanOfTransport(Integer id);

	List<meansOfTransport> getAll();

	meansOfTransport getById(Integer id);

	List<transportCompany> getTransportCompaniesByMeansOfTransportRating(Double rating);
	void affectermeansToCompany(Integer idM, Integer idC);

}
