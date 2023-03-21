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
public class Campingcenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcentre ;
    private String nom ;
    private Integer telephone ;
    private String email ;
    private int prixcamping;
    private Integer nbrMaxParticipant;

    @ManyToMany
    @JsonIgnore
    private List<Activity> activitys;
    @ManyToOne
    @JsonIgnore
    Guide guide;

    @ManyToOne
    @JsonIgnore
    User user ;



}
