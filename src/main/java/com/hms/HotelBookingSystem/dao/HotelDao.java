package com.hms.HotelBookingSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.HotelBookingSystem.entity.Hotel;
import com.hms.HotelBookingSystem.repository.HotelRepository;

@Repository
public class HotelDao {
	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel saveHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public Hotel updateHotel(int hotelId, Hotel hotel )
	{
		Optional<Hotel> optional=hotelRepository.findById(hotelId);
		if(optional.isPresent()) {
			hotel.setHotelId(hotelId);
			hotelRepository.save(hotel);
			return optional.get();
		}
		return null;
	}
	public Hotel findByHotelId(int hotelId) {
		Optional<Hotel> optional=hotelRepository.findById(hotelId);
		if(optional.isPresent()) 
			return optional.get();
		return null;
	}
	
	public boolean deleteHotel(int hotelId) {
		Optional<Hotel> optional=hotelRepository.findById(hotelId);
		if(optional.isPresent()) { 
			hotelRepository.delete(optional.get());
			return true;
		}
		return false;
	}
	
	public List<Hotel> fetchAllHotels(){
		return hotelRepository.findAll();
	}

	
	
}
