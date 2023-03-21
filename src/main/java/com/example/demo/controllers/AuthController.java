package com.example.demo.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;

import com.example.demo.config.MailConfiguration;
import com.example.demo.services.UserService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Dto.JwtResponse;
import com.example.demo.Dto.LoginRequest;
import com.example.demo.Dto.MessageResponse;
import com.example.demo.Dto.SignupRequest;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtUtils;
import com.example.demo.services.UserDetailsImpl;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	@Autowired
	UserService UserService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MailConfiguration mailConfiguration;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws IOException, GeoIp2Exception {

		User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
		String timeLeft= UserService.checkBan(user);
		if(timeLeft!="a")
		{return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is temporarily locked. Please try again in " + timeLeft + ".");}

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			// Reset the failed login attempt count if authentication succeeds
			if (user != null) {
				user.setFailedLoginAttempts(0);
				userRepository.save(user);
			}


			return ResponseEntity.ok(new JwtResponse(jwt,
					userDetails.getId(),
					userDetails.getUsername(),
					userDetails.getEmail(),
					roles));
		} catch (AuthenticationException e) {
			// Authentication failed, increment failed login attempt count
			if (user != null) {
				int failedAttempts = user.getFailedLoginAttempts() + 1;
				user.setFailedLoginAttempts(failedAttempts);
				userRepository.save(user);

				// Check if the user should be banned
				if (failedAttempts >= 3) {

					UserService.timeoutuser(user);
					// User is banned, return error response
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is temporarily locked. Please try again later.");
				}
			}}

		// Authentication failed, return error response
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
	}

	@PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }

	    User user = new User(signUpRequest.getUsername(), 
	               signUpRequest.getEmail(),
	               encoder.encode(signUpRequest.getPassword()));

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	        case "mod":
	          Role modRole = roleRepository.findByName(ERole.ROLE_ORGANISATOR)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(modRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }

	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestParam String resetPasswordRequest) {

		User user = userRepository.findByUsername(resetPasswordRequest)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

		String newPassword = generateRandomPassword();

		user.setPassword(passwordEncoder.encode(newPassword));

		userRepository.save(user);

		String emailBody = "Hello Your password has been reset. Your new password is: " + newPassword + "\n\n" +
				"Please change your password after logging in.\n\n" +
				"Regards,\nThe Team";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("PASSWORD RESET");
		message.setText(emailBody);
		message.setTo(user.getEmail());
		mailConfiguration.sendEmail(message);
		return ResponseEntity.ok("Password reset successfully. Please check your email.");
	}

	private String generateRandomPassword() {
		String password = RandomStringUtils.randomAlphanumeric(8);
		return password;
	}
	

}
