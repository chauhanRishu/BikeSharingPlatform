package com.happyJourney.controller;

import com.happyJourney.entities.Ride;
import com.happyJourney.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride")
@CrossOrigin("*")
public class RideController {

    @Autowired
    private RideService rideService;

    // method to create a new ride
    @PostMapping("/post")
    public Ride createRide(@RequestBody Ride ride) {
        return this.rideService.createRide(ride);
    }


    // method to retrieve all rides
    @GetMapping("/getall")
    public List<Ride> getAllRide() {
        return this.rideService.getAllRides();
    }

    // method to retrieve a ride by ID
    @GetMapping("/get/{id}")
    public Ride getRideById(@PathVariable("id") Long rideId) {
        return this.rideService.getRideById(rideId);
    }

    // method to retrieve all ride created by a captain with captainId
    @GetMapping("/getall/{captainId}")
    public List<Ride> getAllCreateRidesByCaptainId(@PathVariable Long captainId){
        return this.rideService.getAllCreateRidesByCaptainId(captainId);
    }

    // method to change status of a ride
    @GetMapping("/{rideId}/{rideStatus}")
    public Ride changeRideStatus(@PathVariable Long rideId, @PathVariable String rideStatus){
        return this.rideService.changeRideStatus(rideId, rideStatus);
    }

    // method to update a ride
    @PutMapping("/update/{id}")
    public Ride updateCustomer(@PathVariable("id") Long rideId, @RequestBody Ride ride) {
        return this.rideService.updateRide(rideId, ride);
    }

    // method to delete a ride by id
    @DeleteMapping("/delete/{id}")
    public String cancelRide(@PathVariable("id") Long rideId) {
        return this.rideService.cancelRide(rideId);
    }

}
