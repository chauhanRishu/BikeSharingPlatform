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
public class CaptainService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BikeRepo bikeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Method to create a new captain
    public User createCaptain(User captainData) {
        User captainAccount=userRepo.findByUserEmail(captainData.getUserEmail());
        Bike bike=bikeRepo.findByBikePlateNumber(captainData.getBike().getBikePlateNumber());
        if(captainAccount==null && bike==null) // check captain and bike is already exist or not
        {
            User captain=new User();
            captain.setUsersName(captainData.getUsersName());
            captain.setUserAadhaarNumber(captainData.getUserAadhaarNumber());
            captain.setUserMobileNumber(captainData.getUserMobileNumber());
            captain.setUserEmail(captainData.getUserEmail());
            captain.setUserPassword(this.passwordEncoder.encode(captainData.getUserPassword()));
            captain.setDriverLicense(captainData.getDriverLicense());
            captain.setUserRole(UserRole.CAPTAIN);

            Bike newBike = new Bike();
            newBike.setBikeName(captainData.getBike().getBikeName());
            newBike.setBikeColor(captainData.getBike().getBikeColor());
            newBike.setBikeModel(captainData.getBike().getBikeModel());
            newBike.setBikePlateNumber(captainData.getBike().getBikePlateNumber());
            newBike.setUser(captain);
            captain.setBike(newBike);
            return this.userRepo.save(captain);
        }else if(bike!=null){
            System.out.println("Bike is already exist");
            return null;
        }else{
            System.out.println("Captain is already exist");
            return null;
        }

    }

    // Method to retrieve all captains
    public List<User> getAllCaptains() {
        List<User> allCaptains=this.userRepo.findAllByUserRole(UserRole.CAPTAIN);
        return allCaptains;
    }

    // Method to retrieve a captain by ID
    public User getCaptainById(Long captainId) {
        User captainData=this.userRepo.findById(captainId).get();
        return captainData;
    }

    // Method to update a captain
    public User updateCaptain(Long captainId, User updatedCaptain) {
        User existingCaptain = userRepo.findById(captainId).get();
        if (existingCaptain.getUsersName()!=null) {
            User update=new User();
            update.setUserId(captainId);
            update.setUsersName(updatedCaptain.getUsersName());
            update.setUserAadhaarNumber(updatedCaptain.getUserAadhaarNumber());
            update.setUserMobileNumber(updatedCaptain.getUserMobileNumber());
            update.setUserEmail(updatedCaptain.getUserEmail());
            update.setUserPassword(this.passwordEncoder.encode(updatedCaptain.getUserPassword()));
            update.setDriverLicense(updatedCaptain.getDriverLicense());
            update.setUserRole(UserRole.CAPTAIN);
            return this.userRepo.save(update);


        } else {
            System.out.println("Captain not found with captain Id : "+captainId);
            return null; // Or throw an exception indicating captain not found
        }
    }

    // Method to delete a captain by ID
    public String deleteCaptain(Long captainId) {
        this.userRepo.deleteById(captainId);
        return "Captain delete with Id : "+captainId+" successfully!!";
    }


}
