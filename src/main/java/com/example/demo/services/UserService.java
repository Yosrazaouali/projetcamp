package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.User;
import com.maxmind.geoip2.exception.GeoIp2Exception;

public interface UserService {
	
	User addUser(User u);

	User updateUser(User u, Integer id);

	public void deleteUser(Integer id);

	public List<User> getAll();

	public Optional<User> findById(Integer id);
	void getNbrUserParGenre();
	public void timeoutuser(User user) throws GeoIp2Exception, IOException;


	public String checkBan(User user);

}