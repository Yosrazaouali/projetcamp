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
public class OrderA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idOrder;
    private String Clientname;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DatePurshase;
    @Enumerated(EnumType.STRING)
    private ModePayement  modePayment ;

    @OneToOne(mappedBy="order")
    @JsonIgnore
    private Delivery delivery;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Tool> toolSet;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;

}
