package com.hms.HotelBookingSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int userId;
	    private String name;
	    private String email;
	    private String phoneNumber; 
	    
	    @OneToOne
	    private Address address;
}
