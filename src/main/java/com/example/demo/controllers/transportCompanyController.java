package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.transportCompany;
import com.example.demo.services.transportCompanyService;

@RestController
@RequestMapping("/api/transportCompany")
public class transportCompanyController {
	@Autowired
	transportCompanyService transportCompanyService;

	@PostMapping("/add")
	void addTransportCompany(@RequestBody transportCompany transportCompany) {
		transportCompanyService.addTransportCompany(transportCompany);
		//transportCompanyService.sendSmsvalide();
	}

	@PutMapping("/update/{id}")
	void updateTransportCompany(@PathVariable Integer id, @RequestBody transportCompany transportCompany) {
		transportCompanyService.updateTransportCompany(id, transportCompany);
	}

	@DeleteMapping("/delete/{id}")
	void deleteTransportCompany(@PathVariable Integer id) {
		transportCompanyService.deleteTransportCompany(id);

	}

	@GetMapping("/getAll")
	List<transportCompany> getAll() {
		return transportCompanyService.getAll();
	}

	@GetMapping("/getById/{id}")
	transportCompany getById(@PathVariable Integer id) {
		return transportCompanyService.getById(id);
	}

	@GetMapping("/nbr")
	Integer nbrTransportCompany() {
		return transportCompanyService.nbrTransportCompany();
	}

	@GetMapping("/getTransCompByReserv")
	public List<Object[]> countReservationsByTransportCompany() {
		return transportCompanyService.countReservationsByTransportCompany();}
//	@GetMapping("/getDraChnwa/{username}")
//	 public Map<String, List<String>> getTransportCompaniesByMeansOfTransportAndUser(@PathVariable String username){
//		return transportCompanyService.getTransportCompaniesByMeansOfTransportAndUser(username);
//	}

}
