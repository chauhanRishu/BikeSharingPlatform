package com.happyJourney.controller;

import com.happyJourney.entities.Booking;
import com.happyJourney.entities.Ride;
import com.happyJourney.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // method to book a ride
    @PostMapping("/post")
    public Booking bookRide(@RequestBody Booking bookRide) {
        return this.bookingService.bookRide(bookRide);
    }


    // method to retrieve all booking
    @GetMapping("/getall")
    public List<Booking> getAllBooking() {
        return this.bookingService.getAllBookings();
    }

    // method to retrieve a booking by ID
    @GetMapping("/get/{id}")
    public Booking getBookingById(@PathVariable("id") Long bookingId) {
        return this.bookingService.getBookingById(bookingId);
    }


    // method to retrieve all booking of a customer by customerId
    @GetMapping("/getall/{customerId}")
    public List<Booking> getAllRideByCustomerId(@PathVariable Long customerId){
        return this.bookingService.getAllRideByCustomerId(customerId);
    }

    // method to change status of a booking
    @GetMapping("/{bookingId}/{bookingStatus}")
    public Booking changeBookingStatus(@PathVariable Long bookingId, @PathVariable String bookingStatus){
        return this.bookingService.changeBookingStatus(bookingId, bookingStatus);
    }


    // method to delelet a booking by id
    @DeleteMapping("/delete/{id}")
    public String cancelBooking(@PathVariable("id") Long bookingId) {
        return this.bookingService.cancelBooking(bookingId);
    }
}
