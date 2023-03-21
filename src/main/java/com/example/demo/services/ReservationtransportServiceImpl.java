package com.example.demo.services;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ReservationTransport;
import com.example.demo.entities.User;
import com.example.demo.entities.meansOfTransport;
import com.example.demo.repositories.ReservationtransportRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.meanOfTransportRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationtransportServiceImpl implements ReservationtransportService {
	ReservationtransportRepository reservationtransportRepository;
	meanOfTransportRepository meanOftransportRepository;
	UserRepository userRepository;

	//JavaMailSender javaMailSender;

	@Override
	public void addReservationtransport(ReservationTransport reservationTransport, Integer idU, Integer idM) {
		User u = userRepository.findById(idU).get();
		meansOfTransport mt = meanOftransportRepository.findById(idM).get();
		reservationTransport.setMeansOfTransport(mt);
		reservationTransport.setUser(u);
		reservationtransportRepository.save(reservationTransport);

		//SimpleMailMessage message = new SimpleMailMessage();
		//message.setFrom("bacem.mallek999@gmail.com");
		//message.setTo(reservationTransport.getUser().getEmail());
		//message.setSubject("Order confirmation");
		//message.setText("hello , thanks for choosing us ");
		//javaMailSender.send(message);
		//System.out.println("mail sent");
	}
	@Override
	public void updateReservationtransport(Integer id, ReservationTransport reservationTransport) {
		reservationTransport.setIdReservationtransport(id);
		reservationtransportRepository.save(reservationTransport);
	}
	@Override
	public void deleteReservationtransport(Integer id) {
		reservationtransportRepository.deleteById(id);

	}

	@Override
	public List<ReservationTransport> getAll() {
		// TODO Auto-generated method stub
		return reservationtransportRepository.findAll();
	}

	@Override
	public ReservationTransport getById(Integer id) {
		// TODO Auto-generated method stub
		return reservationtransportRepository.findById(id).get();
	}

	@Override
	public Integer nbrReservationTransport() {

		return reservationtransportRepository.findAll().size();
	}

	@Override
	public void affecterMeansOfTransToRese(Integer idM, Integer idR) {
		ReservationTransport rt = reservationtransportRepository.findById(idR).get();
		meansOfTransport mt = meanOftransportRepository.findById(idM).get();
		rt.setMeansOfTransport(mt);
		reservationtransportRepository.save(rt);

	}

	@Override
	public void affecterReservationToUser(Integer idU, Integer idR) {
		User u = userRepository.findById(idU).orElse(null);
		ReservationTransport reservationTransport = reservationtransportRepository.findById(idR).orElse(null);
		reservationTransport.setUser(u);
		reservationtransportRepository.save(reservationTransport);
	}

	@Override
	public ReservationTransport findReserByUsAnMean(Integer id, Integer idTransport) {
		// TODO Auto-generated method stub
		return reservationtransportRepository.findReserByUsAnMean(id, idTransport);}
}
