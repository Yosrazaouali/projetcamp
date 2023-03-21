package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Favorite;
import com.example.demo.services.FavoriteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FavoriteController {
		FavoriteService favoriteService;
	
	@PostMapping("/addFavorite/{meanOfTransId}/{userId}")
	public Favorite addFavorite(@PathVariable Integer meanOfTransId ,@PathVariable Integer userId) {
		return favoriteService.addFavorite(meanOfTransId, userId);
	}
	@GetMapping("/getFavorites/{userId}")
	 List<Favorite> getFavoritesByUserId(@PathVariable Integer userId){
		return favoriteService.getFavoritesByUserId(userId);
	}
	
	@GetMapping("/nbrElementsFavoriteUserh")
	public void nbrElementsFavoriteUser(){
		 favoriteService.nbrElementsFavoriteUser();
	}

}
