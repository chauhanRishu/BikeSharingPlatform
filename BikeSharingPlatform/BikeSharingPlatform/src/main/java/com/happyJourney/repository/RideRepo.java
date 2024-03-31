package com.happyJourney.repository;

import com.happyJourney.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepo extends JpaRepository<Ride, Long> {

    List<Ride> findAllByUserUserId(Long captainId);
}
