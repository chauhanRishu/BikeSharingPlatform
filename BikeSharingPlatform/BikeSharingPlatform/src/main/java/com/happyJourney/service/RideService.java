package com.happyJourney.service;

import com.happyJourney.entities.Ride;
import com.happyJourney.entities.User;
import com.happyJourney.enums.RideStatus;
import com.happyJourney.repository.RideRepo;
import com.happyJourney.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RideService {

    @Autowired
    private RideRepo rideRepo;
    @Autowired
    private UserRepo userRepo;

    // Method to create a new ride
    public Ride createRide(Ride rideData) {
        Ride ride=new Ride();
        ride.setRideStartTime(rideData.getRideStartTime());
        ride.setRideEndTime(rideData.getRideEndTime());
        ride.setRideStartLocation(rideData.getRideStartLocation());
        ride.setRideEndLocation(rideData.getRideEndLocation());
        ride.setRideStatus(RideStatus.Available);
        User captain = this.userRepo.findById(rideData.getUser().getUserId()).get();
        ride.setUser(captain);
        return this.rideRepo.save(ride);
    }

    // Method to retrieve all rides
    public List<Ride> getAllRides() {
        List<Ride> allRides=this.rideRepo.findAll();
        return allRides;
    }

    // Method to retrieve a Ride by ID
    public Ride getRideById(long rideId) {
        Ride rideData=this.rideRepo.findById(rideId).get();
        return rideData;
    }

    // Method to retrieve all created ride by a captain with captainId
    public List<Ride> getAllCreateRidesByCaptainId(Long captainId){
        List<Ride> allRides=this.rideRepo.findAllByUserUserId(captainId);
        return allRides;
    }

    // Method to change status of a ride
    public Ride changeRideStatus(Long rideId, String rideStatus){
        Ride getRide=this.rideRepo.findById(rideId).get();
        if(getRide!=null){
            Ride ride=getRide; // logic check krna hai
            if(Objects.equals(rideStatus,"Booked")){
                ride.setRideStatus(RideStatus.Booked);
            }else{
                ride.setRideStatus(RideStatus.Available);
            }
            return this.rideRepo.save(ride);
        }
        return null;
    }

    // Method to update a ride
    public Ride updateRide(Long rideId, Ride updatedRide) {
        Ride existingRide = rideRepo.findById(rideId).get();
        if (existingRide!=null) {
            Ride update=new Ride();
            update.setRideId(rideId);
            update.setRideStartTime(updatedRide.getRideStartTime());
            update.setRideEndTime(updatedRide.getRideEndTime());
            update.setRideStartLocation(updatedRide.getRideStartLocation());
            update.setRideEndLocation(updatedRide.getRideEndLocation());
            update.setRideStatus(RideStatus.Available);
            return this.rideRepo.save(update);


        } else {
            System.out.println("Ride not found with ride Id : "+rideId);
            return null; // Or throw an exception indicating captain not found
        }
    }

    // Method to cancel a ride by ID
    public String cancelRide(Long rideId) {
        this.rideRepo.deleteById(rideId);
        return "Ride delete with ride Id : "+rideId+" successfully!!";
    }

}
