package com.happyJourney.controller;

import com.happyJourney.entities.Bike;
import com.happyJourney.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bike")
@CrossOrigin("*")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    // method to add a new bike
    @PostMapping("/post")
    public Bike addBike(@RequestBody Bike bike) {
        return this.bikeService.addBike(bike);
    }


    // method to retrieve all bikes
    @GetMapping("/getall")
    public List<Bike> getAllBikes() {
        return this.bikeService.getAllBikes();
    }

    // method to get a bike by ID
    @GetMapping("/get/{id}")
    public Bike getBikeById(@PathVariable("id") Long bikeId) {
        return this.bikeService.getBikeById(bikeId);
    }



    // method to update a bike
    @PutMapping("/update/{id}")
    public Bike updateBike(@PathVariable("id") Long bikeId, @RequestBody Bike bike) {
        return this.bikeService.updateBike(bikeId, bike);
    }

    // method to delete a bike by id
    @DeleteMapping("/delete/{id}")
    public String deleteBike(@PathVariable("id") Long bikeId) {
        return this.bikeService.deleteBike(bikeId);
    }

}
