package com.happyJourney.service;

import com.happyJourney.entities.User;
import com.happyJourney.enums.UserRole;
import com.happyJourney.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Method to create a new customer
    public User createCustomer(User customerData) {
        User customerAccount=userRepo.findByUserEmail(customerData.getUserEmail());
        if(customerAccount==null)
        {
            User customer=new User();
            customer.setUsersName(customerData.getUsersName());
            customer.setUserAadhaarNumber(customerData.getUserAadhaarNumber());
            customer.setUserMobileNumber(customerData.getUserMobileNumber());
            customer.setUserEmail(customerData.getUserEmail());
            customer.setUserPassword(this.passwordEncoder.encode(customerData.getUserPassword()));
            // customer.setDriverLicense(customerData.getDriverLicense());
            customer.setUserRole(UserRole.CUSTOMER);
            return this.userRepo.save(customer);
        }else{
            System.out.println("Email is already exist");
            return null;
        }

    }

    // Method to retrieve all customers
    public List<User> getAllCustomers() {
        List<User> allCustomers=this.userRepo.findAllByUserRole(UserRole.CUSTOMER);
        return allCustomers;
    }

    // Method to retrieve a customer by ID
    public User getCustomerById(long customerId) {
        User customerData=this.userRepo.findById(customerId).get();
        return customerData;
    }

    // Method to update a customer
    public User updateCustomer(Long customerId, User updatedCustomer) {
        User existingCustomer = userRepo.findById(customerId).get();
        if (existingCustomer.getUsersName()!=null) {
            User update=new User();
            update.setUserId(customerId);
            update.setUsersName(updatedCustomer.getUsersName());
            update.setUserAadhaarNumber(updatedCustomer.getUserAadhaarNumber());
            update.setUserMobileNumber(updatedCustomer.getUserMobileNumber());
            update.setUserEmail(updatedCustomer.getUserEmail());
            update.setUserPassword(this.passwordEncoder.encode(updatedCustomer.getUserPassword()));
            // update.setDriverLicense(updatedCustomer.getDriverLicense());
            update.setUserRole(UserRole.CUSTOMER);
            return this.userRepo.save(update);


        } else {
            System.out.println("Customer not found with customer Id : "+customerId);
            return null; // Or throw an exception indicating captain not found
        }
    }

    // Method to delete a customer by ID
    public String deleteCustomer(Long customerId) {
        this.userRepo.deleteById(customerId);
        return "Customer delete with Id : "+customerId+" successfully!!";
    }
}
