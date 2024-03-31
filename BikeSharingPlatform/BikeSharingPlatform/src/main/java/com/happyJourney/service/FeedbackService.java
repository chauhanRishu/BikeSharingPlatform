package com.happyJourney.service;

import com.happyJourney.entities.Feedback;
import com.happyJourney.entities.Ride;
import com.happyJourney.entities.User;
import com.happyJourney.repository.FeedbackRepo;
import com.happyJourney.repository.RideRepo;
import com.happyJourney.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RideRepo rideRepo;


    // Method to add a feedback
    public Feedback addFeedback(Feedback feedbackData) {
        Feedback feedback=new Feedback();
        feedback.setRideRating(feedbackData.getRideRating());
        feedback.setRideComment(feedbackData.getRideComment());

        User customer=this.userRepo.findById(feedbackData.getUser().getUserId()).get();
       // customer.setFeedbacks(feedback.arr); shi krna hai
        feedback.setUser(customer);
        Ride ride=this.rideRepo.findById(feedbackData.getRide().getRideId()).get();
        ride.setFeedback(feedback);
        feedback.setRide(ride);
        return this.feedbackRepo.save(feedback);
    }

    // Method to retrieve all feedbacks
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> allFeedbacks=this.feedbackRepo.findAll();
        return allFeedbacks;
    }

    // Method to retrieve a feedback by ID
    public Feedback getFeedbackById(long feedbackId) {
        Feedback feedbackData=this.feedbackRepo.findById(feedbackId).get();
        return feedbackData;
    }

    // Method to delete a feedback by ID
    public String deleteFeedback(Long feedbackId) {
        this.feedbackRepo.deleteById(feedbackId);
        return "Feedback delete with feedback Id : "+feedbackId+" successfully!!";
    }

}
