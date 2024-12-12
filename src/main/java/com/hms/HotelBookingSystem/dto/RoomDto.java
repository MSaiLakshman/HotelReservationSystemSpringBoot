package com.hms.HotelBookingSystem.dto;

import com.hms.HotelBookingSystem.enums.RoomType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {
	private int roomId;
    private int roomNumber;
    private RoomType type;     
    private double price;      
    private boolean available; 
    private int hotelId;  
}
