package com.hms.HotelBookingSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class UserDto {
	private int userId;
    private String name;
    private String email;
    private String phoneNumber; 
}
