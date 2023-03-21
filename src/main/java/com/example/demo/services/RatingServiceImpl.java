package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Rating;
import com.example.demo.entities.User;
import com.example.demo.entities.meansOfTransport;
import com.example.demo.entities.transportCompany;
import com.example.demo.repositories.RatingRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.meanOfTransportRepository;
import com.example.demo.repositories.transportCompanyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
	RatingRepository ratingRepository;
	UserRepository userRepository;
	meanOfTransportRepository meanOftransportRepository;
	transportCompanyRepository transportCompanyRepository;

	@Override
	public Rating rateMeanOfTransport(Integer idM, Rating rating, Integer idU) {
		meansOfTransport transport = meanOftransportRepository.findById(idM).get();
		User user = userRepository.findById(idU).get();
		rating.setUser(user);
		rating.setMeansOftransport(transport);
		return ratingRepository.save(rating);
	}

	@Override
	public List<transportCompany> getTransportComByRatingSup(int rateD) {
		List<transportCompany> companies = new ArrayList<>();
		List<meansOfTransport> meansOfTransports = meanOftransportRepository.findAll();
		for (meansOfTransport transport : meansOfTransports) {
			List<Rating> ratings = ratingRepository.findByMeansOftransport(transport);
			double moyenneR = calculeDeMoyRating(ratings);
			if (moyenneR >= rateD) {
				companies.add(transport.getTransportCompany());}}
		return companies;}

	private double calculeDeMoyRating(List<Rating> ratings) {
		if (ratings.isEmpty()) {
			return 0;
		}
		int sommeRatings = 0;
		for (Rating rating : ratings) {
			sommeRatings += rating.getRating();

		}
		return sommeRatings / ratings.size();
	}

}
