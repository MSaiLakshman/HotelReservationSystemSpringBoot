package com.hms.HotelBookingSystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.HotelBookingSystem.dao.RoomDao;
import com.hms.HotelBookingSystem.dao.HotelDao;
import com.hms.HotelBookingSystem.dto.RoomDto;
import com.hms.HotelBookingSystem.entity.Hotel;
import com.hms.HotelBookingSystem.entity.Room;
import com.hms.HotelBookingSystem.exception.NotFoundExceeption;
import com.hms.HotelBookingSystem.util.ResponseStructure;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;

    public ResponseEntity<ResponseStructure<Room>> saveRoom(RoomDto roomDto, int hotelID) {
        ResponseStructure<Room> responseStructure = new ResponseStructure<>();
        Hotel hotel = hotelDao.findByHotelId(hotelID);
        
        if (hotel == null) {
        	throw new NotFoundExceeption("Hotel ID not found exception");
        }

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);

        Room savedRoom = roomDao.saveRoom(room);
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setMessage("Room saved successfully");
        responseStructure.setData(savedRoom);

        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }


    public ResponseEntity<ResponseStructure<Room>> findRoomById(int roomId) {
        ResponseStructure<Room> responseStructure = new ResponseStructure<>();

        Room room = roomDao.findByRoomId(roomId);
        if (room == null) {
        	throw new NotFoundExceeption("Room not found exception");
        }

        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Room found successfully");
        responseStructure.setData(room);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Room>>> findAllRooms() {
        ResponseStructure<List<Room>> responseStructure = new ResponseStructure<>();

        List<Room> rooms = roomDao.fetchAllRooms();
        if (rooms.isEmpty()) {
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No rooms available");
            responseStructure.setData(rooms);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }

        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Rooms retrieved successfully");
        responseStructure.setData(rooms);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<String>> deleteRoom(int roomId) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();

        Room room = roomDao.findByRoomId(roomId);
        if (room == null) {
        	throw new NotFoundExceeption("Room ID not found exception");
        }

        roomDao.deleteRoom(roomId);
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Room deleted successfully");
        responseStructure.setData("Room with ID: " + roomId + " has been deleted");
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Room>> updateRoom(int roomId, RoomDto roomDto) {
        ResponseStructure<Room> responseStructure = new ResponseStructure<>();

        Room room = roomDao.findByRoomId(roomId);
        if (room == null) {
        	throw new NotFoundExceeption("Room ID not found exception");
        }

        modelMapper.map(roomDto, room); 
        Room updatedRoom = roomDao.saveRoom(room); 

        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Room updated successfully");
        responseStructure.setData(updatedRoom);

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<List<Room>>> findRoomsByHotelId(int hotelId) {
        ResponseStructure<List<Room>> responseStructure = new ResponseStructure<>();

        Hotel hotel = hotelDao.findByHotelId(hotelId);
        if (hotel == null) {
        	throw new NotFoundExceeption("Hotel ID not found exception");
        }

        List<Room> rooms = roomDao.findRoomsByHotelId(hotelId);
        if (rooms.isEmpty()) {
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No rooms available for this hotel");
            responseStructure.setData(rooms);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }

        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Rooms found successfully");
        responseStructure.setData(rooms);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
}
