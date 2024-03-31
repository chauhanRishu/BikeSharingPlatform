package com.happyJourney.controller;

import com.happyJourney.entities.Feedback;
import com.happyJourney.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // method add a new feedback
    @PostMapping("/post")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return this.feedbackService.addFeedback(feedback);
    }


    // method to retrieve all feedback
    @GetMapping("/getall")
    public List<Feedback> getAllFeedback() {
        return this.feedbackService.getAllFeedbacks();
    }

    // method to retrieve a feedback by iD
    @GetMapping("/get/{id}")
    public Feedback getFeedbackById(@PathVariable("id") Long feedbackId) {
        return this.feedbackService.getFeedbackById(feedbackId);
    }


    // method to delete a feedback by id
    @DeleteMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable("id") Long feedbackId) {
        return this.feedbackService.deleteFeedback(feedbackId);
    }
}
