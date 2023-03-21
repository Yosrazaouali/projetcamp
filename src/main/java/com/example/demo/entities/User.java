package com.example.demo.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@Size(max = 120)
	private String adresse;
   
	private Integer phone;
	@JsonIgnore
	private int failedLoginAttempts;

	private int age;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	@ManyToMany
	private List<transportCompany> transportCompanies;
	@ManyToMany
	List<Favorite> favorites;

	@OneToMany
	List<Publication> publications;

	@ManyToMany
	@JsonIgnore
	List<ChatGroup> chatGroups;

	@OneToMany
	@JsonIgnore
	List<SatisfactionSurvey> satisfactionSurveys;

	@ManyToOne
	OrderA orders;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Claim> claims;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Cart> carts;

	@OneToOne
	private Reservation reservation;
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Rating> ratings;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Campingcenter> campingcenters;
	@OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
	private List<ReservationTransport> reservationTransports = new ArrayList<>();
	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Ban ban ;

	

	public User(String username, String email, String password ) {
		this.username = username;
		this.email = email;
		this.password = password;
	
	}
	

	public Integer getId() {
		return id;
	}

	public void setId() {
		this.id = id;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public User() {
		
	}


	
}
