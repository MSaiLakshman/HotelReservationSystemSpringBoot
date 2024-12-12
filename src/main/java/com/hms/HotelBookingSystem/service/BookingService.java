package com.hms.HotelBookingSystem.service;

import com.hms.HotelBookingSystem.dao.BookingDao;
import com.hms.HotelBookingSystem.dao.RoomDao;
import com.hms.HotelBookingSystem.dao.UserDao;
import com.hms.HotelBookingSystem.dto.BookingDto;
import com.hms.HotelBookingSystem.entity.Booking;
import com.hms.HotelBookingSystem.entity.Room;
import com.hms.HotelBookingSystem.entity.User;
import com.hms.HotelBookingSystem.exception.RoomNotFoundException;
import com.hms.HotelBookingSystem.exception.UserNotFoundException;
import com.hms.HotelBookingSystem.util.ResponseStructure;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoomDao roomDao;

    public ResponseEntity<ResponseStructure<Booking>> saveBooking(BookingDto bookingDto, int userId, int roomId) {
        ResponseStructure<Booking> structure = new ResponseStructure<>();

        User user = userDao.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User Id Not Found"); 
        }

        Room room = roomDao.findByRoomId(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Room not found Exception");
        }

        if (!room.isAvailable()) {
            structure.setStatusCode(HttpStatus.CONFLICT.value());
            structure.setMessage("Room is not available for booking.");
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.CONFLICT);
        }

        Booking booking = modelMapper.map(bookingDto, Booking.class);
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInTime(LocalDateTime.now());
        room.setAvailable(false);

        Booking savedBooking = bookingDao.saveBooking(booking);
        roomDao.saveRoom(room);
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Booking saved successfully!");
        structure.setData(savedBooking);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Boolean>> checkRoomAvailability(int roomId) {
        ResponseStructure<Boolean> structure = new ResponseStructure<>();

        Room room = roomDao.findByRoomId(roomId);
        if (room == null) {
        	throw new RoomNotFoundException("Room not found Exception");
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Room availability checked successfully!");
        structure.setData(room.isAvailable());
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<String>> vacateRoom(int bookingId) {
        ResponseStructure<String> structure = new ResponseStructure<>();

        Booking booking = bookingDao.findByBookingId(bookingId);
        if (booking == null) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("Booking not found with ID: " + bookingId);
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        Room room = booking.getRoom();
        if (room != null) {
            room.setAvailable(true);
            roomDao.saveRoom(room);
        }

        booking.setRoom(null);
        booking.setUser(null);
        booking.setCheckOutTime(LocalDateTime.now());
        bookingDao.saveBooking(booking);

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Room vacated successfully!");
        structure.setData("Booking with ID: " + bookingId + " has been vacated.");
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Booking>> findBookingById(int bookingId) {
        ResponseStructure<Booking> structure = new ResponseStructure<>();

        Booking booking = bookingDao.findByBookingId(bookingId);
        if (booking == null) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("Booking not found with ID: " + bookingId);
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Booking found successfully!");
        structure.setData(booking);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Booking>> updateBooking(int bookingId, BookingDto bookingDto) {
        ResponseStructure<Booking> structure = new ResponseStructure<>();

        Booking booking = bookingDao.findByBookingId(bookingId);
        if (booking == null) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("Booking not found with ID: " + bookingId);
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        modelMapper.map(bookingDto, booking);
        Booking updatedBooking = bookingDao.saveBooking(booking);

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Booking updated successfully!");
        structure.setData(updatedBooking);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<String>> deleteBooking(int bookingId) {
        ResponseStructure<String> structure = new ResponseStructure<>();

        Booking booking = bookingDao.findByBookingId(bookingId);
        if (booking == null) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("Booking not found with ID: " + bookingId);
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        bookingDao.deleteBooking(bookingId);
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Booking deleted successfully!");
        structure.setData("Booking with ID: " + bookingId + " has been deleted.");
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Booking>>> findAllBookings() {
        ResponseStructure<List<Booking>> structure = new ResponseStructure<>();

        List<Booking> bookings = bookingDao.fetchAllBookings();
        if (bookings.isEmpty()) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("No bookings found.");
            structure.setData(bookings);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Bookings retrieved successfully!");
        structure.setData(bookings);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
}
