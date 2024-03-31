package com.happyJourney.entities;

import com.happyJourney.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long bookingId;

    @Column(name = "status")
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "customer_Id")
    private User user;

    @OneToOne
    @JoinColumn(name = "ride_Id")
    private Ride ride;
}
