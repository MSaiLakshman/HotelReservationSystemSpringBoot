package com.hms.HotelBookingSystem.controller;

import com.hms.HotelBookingSystem.dto.RoomDto;
import com.hms.HotelBookingSystem.entity.Room;
import com.hms.HotelBookingSystem.service.RoomService;
import com.hms.HotelBookingSystem.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add/{hotelId}")
    public ResponseEntity<ResponseStructure<Room>> saveRoom(@RequestBody RoomDto roomDto, @PathVariable int hotelId) {
        return roomService.saveRoom(roomDto, hotelId);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ResponseStructure<Room>> findRoomById(@PathVariable int roomId) {
        return roomService.findRoomById(roomId);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<Room>>> findAllRooms() {
        return roomService.findAllRooms();
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ResponseStructure<String>> deleteRoom(@PathVariable int roomId) {
        return roomService.deleteRoom(roomId);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<ResponseStructure<Room>> updateRoom(@PathVariable int roomId, @RequestBody RoomDto roomDto) {
        return roomService.updateRoom(roomId, roomDto);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<ResponseStructure<List<Room>>> findRoomsByHotelId(@PathVariable int hotelId) {
        return roomService.findRoomsByHotelId(hotelId);
    }
}
