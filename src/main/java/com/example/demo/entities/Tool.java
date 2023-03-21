package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_outil", nullable = false)
    private Integer idOutil;
    @Enumerated(EnumType.STRING)
    private Function fuction;
    private String toolName;
    private String type;
    private  float price;
    private  String  dimension;
    private float weight;
    private  String brand;
    @ManyToMany(mappedBy="toolSet", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OrderA> orders;

}
