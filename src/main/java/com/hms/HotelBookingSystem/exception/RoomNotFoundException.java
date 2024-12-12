package com.hms.HotelBookingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class RoomNotFoundException extends RuntimeException {
	String message;
}
