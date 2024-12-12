package com.hms.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.HotelBookingSystem.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	 List<Room> findByHotel_HotelId(int hotelId);
}
