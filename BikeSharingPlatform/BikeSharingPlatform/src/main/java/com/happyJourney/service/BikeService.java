package com.happyJourney.service;

import com.happyJourney.entities.Bike;
import com.happyJourney.entities.User;
import com.happyJourney.enums.UserRole;
import com.happyJourney.repository.BikeRepo;
import com.happyJourney.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    private BikeRepo bikeRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to add a new bike
    public Bike addBike(Bike bikeData) {
        Bike bike=bikeRepo.findByBikePlateNumber(bikeData.getBikePlateNumber());
        User captain=userRepo.findByUserEmail(bikeData.getUser().getUserEmail());
        if(bike==null && captain==null)// also check captain is existing or not
        {
            System.out.println("RRRRRRRRRRRRRRRRRRAAAAAAAAAAAAAJJJJJJJJJJJJJJJJJ");
            Bike newBike=new Bike();
            newBike.setBikeName(bikeData.getBikeName());
            newBike.setBikeColor(bikeData.getBikeColor());
            newBike.setBikeModel(bikeData.getBikeModel());
            newBike.setBikePlateNumber(bikeData.getBikePlateNumber());

            System.out.println("rrrrrrraaaaaaaaaaajjjjjjjjjjjjj");

            User newUser = new User();
            newUser.setUsersName(bikeData.getUser().getUsersName());
            newUser.setUserAadhaarNumber(bikeData.getUser().getUserAadhaarNumber());
            newUser.setUserMobileNumber(bikeData.getUser().getUserMobileNumber());
            newUser.setUserEmail(bikeData.getUser().getUserEmail());
            newUser.setUserPassword(this.passwordEncoder.encode(bikeData.getUser().getUserPassword()));
            newUser.setDriverLicense(bikeData.getUser().getDriverLicense());
            newUser.setUserRole(UserRole.CAPTAIN);
            newUser.setBike(newBike);
            newBike.setUser(newUser);
            return this.bikeRepo.save(newBike);
        }else if(captain!=null){
            System.out.println("Captain is already exist");
            return null;
        }else{
            System.out.println("Bike is already exist");
            return null;
        }

    }

    // Method to retrieve all bikes
    public List<Bike> getAllBikes() {
        List<Bike> allBikes=this.bikeRepo.findAll();
        return allBikes;
    }

    // Method to retrieve a Bike by ID
    public Bike getBikeById(long bikeId) {
        Bike bikeData=this.bikeRepo.findById(bikeId).get();
        if(bikeData!=null){
            return bikeData;
        } else {
            System.out.println("Bike not exist with bike Id: "+bikeId);
            return null;
        }
    }

    // Method to update a bike
    public Bike updateBike(Long bikeId, Bike updatedBike) {
        Bike existingBike = bikeRepo.findById(bikeId).get();
        if (existingBike.getBikeName()!=null) {
            Bike update=new Bike();
            update.setBikeId(bikeId);
            update.setBikeName(updatedBike.getBikeName());
            update.setBikeColor(updatedBike.getBikeColor());
            update.setBikeModel(updatedBike.getBikeModel());
            update.setBikePlateNumber(updatedBike.getBikePlateNumber());
            return this.bikeRepo.save(update);


        } else {
            System.out.println("Bike not found with bike Id : "+bikeId);
            return null; // Or throw an exception indicating captain not found
        }
    }

    // Method to delete a bike by ID
    public String deleteBike(Long bikeId) {

        Bike bike=bikeRepo.findById(bikeId).get();
        if(bike!=null){
            this.bikeRepo.deleteById(bikeId);
            return "Bike delete with bike Id : "+bikeId+" successfully!!";
        }else {
            return "Bike does not exist with bike Id : "+bikeId+" ";
        }
    }

}
