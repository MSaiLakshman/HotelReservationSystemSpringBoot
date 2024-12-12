package com.hms.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.HotelBookingSystem.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
