package com.happyJourney.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bike")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long bikeId;
    @Column(name = "name")
    private String bikeName;
    @Column(name = "color")
    private String bikeColor;
    @Column(name = "model")
    private String bikeModel;
    @Column(name = "number_plate")
    private String bikePlateNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="captain_Id")
    private User user;
}
