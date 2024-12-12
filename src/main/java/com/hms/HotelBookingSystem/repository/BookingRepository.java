package com.hms.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.HotelBookingSystem.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
