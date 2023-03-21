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
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delv_person", nullable = false)
    private Integer idDelvPerson;
    private String nameDelvPerson;
    private Integer workHours;
    private Boolean availablity;
    private String Delvlocation;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Delivery> deliverySet;

}
