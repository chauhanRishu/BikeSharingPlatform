package com.happyJourney.repository;

import com.happyJourney.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUserUserId(Long customerId);
}
