package com.hms.HotelBookingSystem.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
	 private int bookingId;
	    
	    private LocalDateTime checkInTime;   
	    private LocalDateTime checkOutTime;  
	    private int numberOfGuests;          
	    private double totalPrice;  
}
