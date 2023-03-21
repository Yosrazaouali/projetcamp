package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Claim {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idClaim ;
    private String DescriptionOfTheClaim ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateOfTheClaim ;
    @Enumerated
    private TypeOfTheClaim TypeOfTheClaim ;
    @ManyToMany(mappedBy = "claims", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users;
}
