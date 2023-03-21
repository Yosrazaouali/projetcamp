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
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublication;
    private String contenuPublication;
    @Enumerated(EnumType.STRING)
    private Reactions reaction;

    @OneToMany
    @JsonIgnore
    List<Comment> comments;

    @ManyToOne
    @JsonIgnore
    User user;




}
