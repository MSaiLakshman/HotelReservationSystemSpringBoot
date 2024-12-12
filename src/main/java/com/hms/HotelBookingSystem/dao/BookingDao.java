package com.hms.HotelBookingSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hms.HotelBookingSystem.entity.Booking;
import com.hms.HotelBookingSystem.repository.BookingRepository;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepository bookingRepository;
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	public Booking updateBooking(int bookingId, Booking booking) {
		Optional<Booking> optional=bookingRepository.findById(bookingId);
		if(optional.isPresent()) {
			booking.setBookingId(bookingId);
			bookingRepository.save(booking);
			return optional.get();
		}
		return null;
	}
	public Booking findByBookingId(int bookingId) {
		Optional<Booking> optional=bookingRepository.findById(bookingId);
		if(optional.isPresent()) 
			return optional.get();
		return null;
	}
	
	public boolean deleteBooking(int bookingId) {
		Optional<Booking> optional=bookingRepository.findById(bookingId);
		if(optional.isPresent()) { 
			bookingRepository.delete(optional.get());
			return true;
		}
		return false;
	}
	
	public List<Booking> fetchAllBookings(){
		return bookingRepository.findAll();
	}
}
