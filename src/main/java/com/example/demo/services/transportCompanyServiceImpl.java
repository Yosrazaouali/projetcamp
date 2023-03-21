package com.example.demo.services;

import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.entities.transportCompany;
import com.example.demo.repositories.ReservationtransportRepository;
import com.example.demo.repositories.transportCompanyRepository;

@Service
public class transportCompanyServiceImpl implements transportCompanyService {
	@Autowired
	transportCompanyRepository transportCompanyRepository;
	@Autowired
	ReservationtransportRepository reservationtransportRepository;

	@Override
	public void addTransportCompany(transportCompany transportCompany) {
		transportCompanyRepository.save(transportCompany);

	}
	@Override
	public void sendSmsvalide() {
	 final   String ACCOUNT_SID = "ACcad09eae3feb4247b8d9f826c59aea04";
	  final String AUTH_TOKEN = "d689580fb8ba9decdba2488717a626ef";


		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message msg = Message.creator(new PhoneNumber("+21628226900"),new PhoneNumber("+15674092249"),("Transport Company is added")).create();

	}

	@Override
	public void updateTransportCompany(Integer id, transportCompany transportCompany) {
		transportCompany.setIdCompany(id);
		transportCompanyRepository.save(transportCompany);

	}

	@Override
	public void deleteTransportCompany(Integer id) {
		transportCompanyRepository.deleteById(id);

	}

	@Override
	public List<transportCompany> getAll() {
		// TODO Auto-generated method stub
		return transportCompanyRepository.findAll();
	}

	@Override
	public transportCompany getById(Integer id) {
		// TODO Auto-generated method stub
		return transportCompanyRepository.findById(id).get();
	}

	@Override
	public Integer nbrTransportCompany() {
		return transportCompanyRepository.findAll().size();

	}

	@Scheduled(cron = "* * */1 * * *")
	@Override
	public List<Object[]> countReservationsByTransportCompany() {
		// TODO Auto-generated method stub

		return transportCompanyRepository.countReservationsByTransportCompany();
	}

}
