package com.hms.HotelBookingSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int bookingId;
	    
	    private LocalDateTime checkInTime;   
	    private LocalDateTime checkOutTime;  
	    private int numberOfGuests;          
	    private double totalPrice;           
	    
	    @ManyToOne
	    private Room room;  
	    
	    @ManyToOne
	    private User user;
	    
	    public boolean isCheckedOut() {
	        return checkOutTime != null && checkOutTime.isBefore(LocalDateTime.now());
	    }

	   
}
