package com.happyJourney.repository;

import com.happyJourney.entities.User;
import com.happyJourney.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUserEmail(String email);

    User findByUserMobileNumber(String contact);
    User findByUserRole(UserRole userRole);
    List<User> findAllByUserRole(UserRole userRole);


}
