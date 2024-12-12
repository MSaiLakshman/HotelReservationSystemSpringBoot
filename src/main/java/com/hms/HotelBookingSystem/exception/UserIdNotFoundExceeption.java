package com.hms.HotelBookingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserIdNotFoundExceeption extends RuntimeException {
	private String message;
}
