package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class transportCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCompany;
	@NotBlank
	private String name;
	@ManyToMany(mappedBy = "transportCompanies" , cascade = CascadeType.ALL)
	private List<User> users;
	@OneToMany(mappedBy = "transportCompany" , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<meansOfTransport> meansOfTransports;

}
