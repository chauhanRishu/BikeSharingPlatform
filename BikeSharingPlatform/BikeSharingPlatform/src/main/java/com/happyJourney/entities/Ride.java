package com.happyJourney.entities;

import com.happyJourney.enums.RideStatus;
import com.happyJourney.service.RideService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ride")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long rideId;
    @Column(name = "start_time")
    private LocalDateTime rideStartTime;
    @Column(name = "end_time")
    private LocalDateTime rideEndTime;
    @Column(name = "startlocation")
    private String rideStartLocation;
    @Column(name = "endlocation")
    private String rideEndLocation;
    @Column(name = "status")
    private RideStatus rideStatus;

    // using mapping
    @ManyToOne
    @JoinColumn(name = "captain_Id")
    private User user;

    @OneToOne(mappedBy = "ride", cascade = CascadeType.ALL)
    private Booking booking;

    @OneToOne(mappedBy = "ride",cascade = CascadeType.ALL)
    private Feedback feedback;

}
