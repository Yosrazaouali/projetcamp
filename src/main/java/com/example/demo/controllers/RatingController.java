package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Rating;
import com.example.demo.entities.transportCompany;
import com.example.demo.services.RatingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RatingController {
	RatingService ratingService;
	
	@PostMapping("/addRating/{idM}/{idU}")
	public Rating rateMeanOfTransport(@PathVariable Integer idM,@RequestBody Rating rating,@PathVariable Integer idU) {
		return ratingService.rateMeanOfTransport(idM, rating, idU);
	}
	@GetMapping("/CompanyTransport/{rateD}")
	public List<transportCompany> getTransportComByRatingSup(@PathVariable int rateD){
		return ratingService.getTransportComByRatingSup(rateD);
	}

	

}
