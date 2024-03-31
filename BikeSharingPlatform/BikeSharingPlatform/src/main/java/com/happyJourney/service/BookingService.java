package com.happyJourney.service;

import com.happyJourney.entities.Booking;
import com.happyJourney.entities.Ride;
import com.happyJourney.entities.User;
import com.happyJourney.enums.BookingStatus;
import com.happyJourney.repository.BookingRepo;
import com.happyJourney.repository.RideRepo;
import com.happyJourney.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RideRepo rideRepo;

    // Method to book a ride
    public Booking bookRide(Booking bookingData) {
        Booking booking=new Booking();
        booking.setBookingStatus(BookingStatus.Pending);
        User customer=this.userRepo.findById(bookingData.getUser().getUserId()).get();
        booking.setUser(customer);
        Ride ride=this.rideRepo.findById(bookingData.getRide().getRideId()).get();
        booking.setRide(ride);
        return this.bookingRepo.save(booking);
    }

    // Method to retrieve all bookings
    public List<Booking> getAllBookings() {
        List<Booking> allBookings=this.bookingRepo.findAll();
        return allBookings;
    }

    // Method to retrieve a booking by ID
    public Booking getBookingById(long bookingId) {
        Booking bookingData=this.bookingRepo.findById(bookingId).get();
        return bookingData;
    }

    // Method to retrieve all booking of a captain
    public List<Booking> getAllRideByCustomerId(Long customerId){
        List<Booking> allBooking=this.bookingRepo.findAllByUserUserId(customerId);
        return allBooking;
    }

    // Method to change status of a booking
    public Booking changeBookingStatus(Long bookingId, String bookingStatus){
        Booking getBooking=this.bookingRepo.findById(bookingId).get();
        if(getBooking!=null){
            Booking booking=getBooking; // logic check krna hai
            if(Objects.equals(bookingStatus,"Confirm")){
                booking.setBookingStatus(BookingStatus.Confirm);
            }else{
                booking.setBookingStatus(BookingStatus.Pending);
            }
            return this.bookingRepo.save(booking);
        }
        return null;
    }

    // Method to cancel a booking by ID
    public String cancelBooking(Long bookingId) {
        this.bookingRepo.deleteById(bookingId);
        return "Booking cancel with booking Id : "+bookingId+" successfully!!";
    }

}
