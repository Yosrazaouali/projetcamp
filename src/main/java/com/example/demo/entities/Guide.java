package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idguide;
    private String nomguide;
    private String prenomguide;
    private String ville;
    private Integer tarifHoraire;
    private String adresse;
    private int telephone;
    private String email;
    @OneToMany(cascade = CascadeType.ALL , mappedBy ="guide")
    @JsonIgnore
    private List<Campingcenter> campingcenters;

}
