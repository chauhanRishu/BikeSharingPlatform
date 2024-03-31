package com.happyJourney.repository;

import com.happyJourney.entities.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepo extends JpaRepository<Bike, Long> {

    Bike findByBikePlateNumber(String plateNumber);
}
