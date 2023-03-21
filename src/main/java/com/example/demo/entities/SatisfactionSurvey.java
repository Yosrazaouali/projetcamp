package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class SatisfactionSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idS;
    private Long nbStars;
    private String review;
    @ManyToOne
    @JsonIgnore
    User user;
}
