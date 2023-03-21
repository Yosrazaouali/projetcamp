package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCart ;



    @OneToMany
    @JsonIgnore
    private List<Meal> ListOfMeals;
    @ManyToOne
    @JsonIgnore
    User user ;
}
