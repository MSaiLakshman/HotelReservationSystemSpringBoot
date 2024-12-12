package com.hms.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.HotelBookingSystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
