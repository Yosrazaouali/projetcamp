package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Rating;
import com.example.demo.entities.meansOfTransport;
import com.example.demo.entities.transportCompany;

public interface RatingService {

	Rating rateMeanOfTransport(Integer idM, Rating rating, Integer idU);

	List<transportCompany> getTransportComByRatingSup(int rateD);
}
