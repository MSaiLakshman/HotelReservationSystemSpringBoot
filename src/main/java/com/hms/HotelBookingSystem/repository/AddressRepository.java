package com.hms.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.HotelBookingSystem.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
