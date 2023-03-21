package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.ReservationTransport;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.meansOfTransport;
import com.example.demo.entities.transportCompany;
import com.example.demo.repositories.meanOfTransportRepository;
import com.example.demo.repositories.transportCompanyRepository;

@Service
public class meansOfTransportServiceImpl implements meansOfTransportService {
    @Autowired
    meanOfTransportRepository transportRepository;
    @Autowired
    transportCompanyRepository CompanyRepository;


    @Override
    public void addmeanOfTransport(meansOfTransport meanOftransport) {
        transportRepository.save(meanOftransport);

    }

    @Override
    public void updatemeanOfTransport(Integer id, meansOfTransport meanOfTransport) {
        meanOfTransport.setIdTransport(id);
        transportRepository.save(meanOfTransport);
    }

    @Override
    public void deletemeanOfTransport(Integer id) {
        transportRepository.deleteById(id);

    }

    @Override
    public List<meansOfTransport> getAll() {
        // TODO Auto-generated method stub
        return transportRepository.findAll();
    }

    @Override
    public meansOfTransport getById(Integer id) {
        // TODO Auto-generated method stub
        return transportRepository.findById(id).get();
    }

	@Override
	public List<transportCompany> getTransportCompaniesByMeansOfTransportRating(Double rating) {
		
		return transportRepository.getTransportCompaniesByMeansOfTransportRating(rating);
	}
    @Override
    public void affectermeansToCompany(Integer idM, Integer idC) {
        meansOfTransport mr = transportRepository.findById(idM).orElse(null);
        transportCompany Company = CompanyRepository.findById(idC).orElse(null);
        mr.setTransportCompany(Company);
        transportRepository.save(mr);
    }

}
