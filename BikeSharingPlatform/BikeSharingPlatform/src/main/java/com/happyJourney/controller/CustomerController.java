package com.happyJourney.controller;

import com.happyJourney.entities.User;
import com.happyJourney.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // method to create a new customer
    @PostMapping("/post")
    public User createCustomer(@RequestBody User customer) {
        return this.customerService.createCustomer(customer);
    }


    // method to retrieve all customers
    @GetMapping("/getall")
    public List<User> getAllCustomer() {
        return this.customerService.getAllCustomers();
    }

    // method to retrieve customer by ID
    @GetMapping("/get/{id}")
    public User getCustomerById(@PathVariable("id") Long customerId) {
        return this.customerService.getCustomerById(customerId);
    }


    // method to update a customer
    @PutMapping("/update/{id}")
    public User updateCustomer(@PathVariable("id") Long customerId, @RequestBody User customer) {
        return this.customerService.updateCustomer(customerId, customer);
    }

    // method to delete a customer by id
    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long customerId) {
        return this.customerService.deleteCustomer(customerId);
    }

}
