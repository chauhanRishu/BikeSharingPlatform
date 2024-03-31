package com.happyJourney.service;

import com.happyJourney.entities.User;
import com.happyJourney.enums.UserRole;
import com.happyJourney.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminAccount(){

        User adminAccount=userRepo.findByUserRole(UserRole.ADMIN);
        if(adminAccount==null)
        {
            User admin=new User();
            admin.setUserId(1);
            admin.setUsersName("rishu");
            admin.setUserEmail("rishu@gmail.com");
            admin.setUserPassword(this.passwordEncoder.encode("rishu@123"));
            admin.setUserRole(UserRole.ADMIN);
            this.userRepo.save(admin);
        }

    }

}
