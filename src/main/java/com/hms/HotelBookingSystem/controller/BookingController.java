package com.hms.HotelBookingSystem.controller;

import com.hms.HotelBookingSystem.dto.BookingDto;
import com.hms.HotelBookingSystem.entity.Booking;
import com.hms.HotelBookingSystem.service.BookingService;
import com.hms.HotelBookingSystem.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	
    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<ResponseStructure<Booking>> bookRoom(@RequestBody BookingDto bookingDto,
                                                                @RequestParam int userId,
                                                                @RequestParam int roomId) {
        return bookingService.saveBooking(bookingDto, userId, roomId);
    }

    @GetMapping("/availability/{roomId}")
    public ResponseEntity<ResponseStructure<Boolean>> checkRoomAvailability(@PathVariable int roomId) {
        return bookingService.checkRoomAvailability(roomId);
    }

    @PutMapping("/vacate/{bookingId}")
    public ResponseEntity<ResponseStructure<String>> vacateRoom(@PathVariable int bookingId) {
        return bookingService.vacateRoom(bookingId);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable int bookingId) {
        return bookingService.findBookingById(bookingId);
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<ResponseStructure<Booking>> updateBooking(@PathVariable int bookingId,
                                                                     @RequestBody BookingDto bookingDto) {
        return bookingService.updateBooking(bookingId, bookingDto);
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable int bookingId) {
        return bookingService.deleteBooking(bookingId);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings() {
        return bookingService.findAllBookings();
    }
}