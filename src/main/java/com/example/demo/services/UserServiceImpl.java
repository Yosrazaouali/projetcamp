package com.example.demo.services;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Ban;
import com.example.demo.repositories.BanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Gender;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BanRepository banRepository;

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);


    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id);
    }

	@Override
	public User addUser(User u) {
		// TODO Auto-generated method stub
		return userRepository.save(u);
	}

	@Override
	public User updateUser(User u, Integer id) {
		// TODO Auto-generated method stub
		u.setId(id);
		return userRepository.save(u);
	}

	@Override
	public void getNbrUserParGenre() {
		log.info("--- Nombre des Users MASCULIN :" + userRepository.nbreByGenre(Gender.Masculin));
		log.info("--- Nombre des Users FEMININ : " + userRepository.nbreByGenre(Gender.Feminin));}
    public static final int MAX_FAILED_ATTEMPTS = 3;

    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

    @Autowired
    private UserRepository repo;

    @Override
    public void timeoutuser(User user) throws GeoIp2Exception, IOException {

        Ban ban = new Ban();
        ban.setLastFailedLoginAttempt(LocalDateTime.now());
        ban.setExpiryTime(LocalDateTime.now().plusMinutes(100));
        ban.setUser(user);
        user.setBan(ban);
        banRepository.save(ban);
        userRepository.save(user);
    }

    @Override
    public String checkBan(User user) {


        if (user != null && user.getBan() != null && user.getBan().getExpiryTime() != null &&
                LocalDateTime.now().isBefore(user.getBan().getExpiryTime())) {
            // User is banned, return error response
            Duration remainingTime = Duration.between(LocalDateTime.now(), user.getBan().getExpiryTime());
            String timeLeft = String.format("%d minutes, %d seconds", remainingTime.toMinutes(), remainingTime.getSeconds() % 60);
            return timeLeft;

        }
        return "a";
    }

}