package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ReservationTransport;
import com.example.demo.services.ReservationtransportService;

@RestController
public class ReservationtransportController {
	@Autowired
	ReservationtransportService reservationtransportService;

	@PostMapping("/add/{idU}/{idM}")
	void addmeanOfTransport(@RequestBody ReservationTransport Reservationtransport ,@PathVariable Integer idU ,@PathVariable Integer idM) {
		reservationtransportService.addReservationtransport(Reservationtransport, idU, idM);
	}

	@PutMapping("/update/{id}")
	void updatemeanOfTransport(@PathVariable Integer id, @RequestBody ReservationTransport Reservationtransport) {
		reservationtransportService.updateReservationtransport(id, Reservationtransport);
	}

	@DeleteMapping("/delete/{id}")
	void deleteTransportCompany(@PathVariable Integer id) {
		reservationtransportService.deleteReservationtransport(id);

	}

	@GetMapping("/getAll")
	List<ReservationTransport> getAll() {
		return reservationtransportService.getAll();
	}

	@GetMapping("/getById/{id}")
	ReservationTransport getById(@PathVariable Integer id) {
		return reservationtransportService.getById(id);
	}

	@GetMapping("/nbr")
	Integer nbrReservationtransport() {

		return reservationtransportService.nbrReservationTransport();
	}

	@GetMapping("/affecterMeanToReser/{idM}/{idR}")
	public void affecterMeansOfTransToRese(@PathVariable Integer idM, @PathVariable Integer idR) {
		reservationtransportService.affecterMeansOfTransToRese(idM, idM);
	}

	@GetMapping("/affecterReservationToUser/{idU}/{idR}")
	public void affecterReservationToUser(@PathVariable Integer idU, @PathVariable Integer idR) {
		reservationtransportService.affecterReservationToUser(idU, idR);
	}
	@GetMapping("/getReservaByUsAndMean/{id}/{idTransport}")
	public ReservationTransport findReserByUsAnMean(@PathVariable Integer id,@PathVariable Integer idTransport) {
		return reservationtransportService.findReserByUsAnMean(id, idTransport);
	}

}
