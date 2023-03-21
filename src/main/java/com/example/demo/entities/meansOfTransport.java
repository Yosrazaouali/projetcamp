package com.example.demo.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Data;

@Entity
@Data
public class meansOfTransport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransport;
	private String nameTransport;
	private Integer capacity;
	@Enumerated(EnumType.STRING)
	private Type type;
	@Lob
	private byte[] image;

	@ManyToOne
	@JsonIgnore
	private transportCompany transportCompany;
	
	@OneToMany(mappedBy = "meansOftransport" , cascade = CascadeType.ALL)
	private List<Rating> ratings;
	

}
