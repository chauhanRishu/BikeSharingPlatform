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
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long feedbackId;
    @Column(name = "rating")
    private int rideRating;
    @Column(name = "comment")
    private String rideComment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
}
