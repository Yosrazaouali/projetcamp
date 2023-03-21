package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.entities.Favorite;

public interface FavoriteService {
	 Favorite addFavorite(Integer meanOfTransId , Integer userId);
	 List<Favorite> getFavoritesByUserId(Integer userId);
	 void nbrElementsFavoriteUser();
	
	

}
