package com.happyJourney.controller;

import com.happyJourney.entities.User;
import com.happyJourney.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/captain")
@CrossOrigin("*")
public class CaptainController {

    @Autowired
    private CaptainService captainService;

    // method to create a new captain
    @PostMapping("/post")
    public User createCaptain(@RequestBody User captain) {
        return this.captainService.createCaptain(captain);
    }


    // method to retrieve all captains
    @GetMapping("/getall")
    public List<User> getAllCaptains() {
        return this.captainService.getAllCaptains();
    }

    // method to retrieve captain by ID
    @GetMapping("/get/{id}")
    public User getCaptainById(@PathVariable("id") Long captainId) {
        return this.captainService.getCaptainById(captainId);
    }



    // method tp update a captain
    @PutMapping("/update/{id}")
    public User updateCaptain(@PathVariable("id") Long captainId, @RequestBody User captain) {
       return this.captainService.updateCaptain(captainId, captain);
    }

    // method to delete a captain by id
    @DeleteMapping("/delete/{id}")
    public String deleteCaptain(@PathVariable("id") Long captainId) {
        return this.captainService.deleteCaptain(captainId);
    }

}
