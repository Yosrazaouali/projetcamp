package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idactivity ;
    private String nom ;
    private Integer telephone ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateactivity;

    @Enumerated(EnumType.STRING)
    private TypeActivity TypeActivity ;

    @ManyToMany(mappedBy="activitys")
    @JsonIgnore
    private List<Campingcenter> campingcenters ;


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Claim>claims;
}
