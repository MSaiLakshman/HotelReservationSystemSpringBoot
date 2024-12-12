package com.hms.HotelBookingSystem.entity;

import com.hms.HotelBookingSystem.enums.RoomType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	private int roomNumber;
	@Enumerated(EnumType.STRING)
	private RoomType type;
	private double price;
	private boolean available;
	
	@ManyToOne
	private Hotel hotel;
	
}
