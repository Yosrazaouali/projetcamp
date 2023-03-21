package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Favorite;
import com.example.demo.entities.User;
import com.example.demo.repositories.FavoriteRepository;
import com.example.demo.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
	FavoriteRepository favoriteRepository;
	UserRepository userRepository;
	@Override
	public Favorite addFavorite(Integer meanOfTransId, Integer userId) {
		Favorite favorite=favoriteRepository.findByUserIdAndMeanOfTransportId(userId, meanOfTransId);
		if(favorite !=null) {
			throw new IllegalStateException("the item is already in the favorites");
		}
		else {
			favorite = new Favorite();
			favorite.setMeanOfTransportId(meanOfTransId);
			favorite.setUserId(userId);
			return favoriteRepository.save(favorite);
		}
	}
	@Override
	public List<Favorite> getFavoritesByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return favoriteRepository.findByUserId(userId);
	}
	@Override
	public void nbrElementsFavoriteUser() {
	    List<User> users = userRepository.findAll();

	    for(User u:users)
		{
	    	 int count = favoriteRepository.findByUserId(u.getId()).size();
			 log.info("User " + u.getUsername() + " has " + count + " favorite elements.");
	    }
	}
	
	
	

}