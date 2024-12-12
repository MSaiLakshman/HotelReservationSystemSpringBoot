package com.hms.HotelBookingSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.HotelBookingSystem.entity.Room;
import com.hms.HotelBookingSystem.repository.RoomRepository;

@Repository
public class RoomDao {

    @Autowired
    private RoomRepository roomRepository;

    
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    
    public Room updateRoom(int roomId, Room room) {
        Optional<Room> optional = roomRepository.findById(roomId);
        if (optional.isPresent()) {
            room.setRoomId(roomId);
            return roomRepository.save(room);
        }
        return null;
    }

    
    public Room findByRoomId(int roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    
    public boolean deleteRoom(int roomId) {
        Optional<Room> optional = roomRepository.findById(roomId);
        if (optional.isPresent()) {
            roomRepository.delete(optional.get());
            return true;
        }
        return false;
    }

    
    public List<Room> findRoomsByHotelId(int hotelId) {
        return roomRepository.findByHotel_HotelId(hotelId);
    }

    
    public List<Room> fetchAllRooms() {
        return roomRepository.findAll();
    }
}
