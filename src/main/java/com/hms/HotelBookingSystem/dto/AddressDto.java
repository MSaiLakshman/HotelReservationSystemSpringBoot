package com.hms.HotelBookingSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private int addressId;
	private int houseNumber;
	private String area;
	private String city;
	private String state;
	private String country;
	private long pincode;
}
