package com.hms.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hms.HotelBookingSystem.dto.HotelDto;
import com.hms.HotelBookingSystem.entity.Hotel;
import com.hms.HotelBookingSystem.service.HotelService;
import com.hms.HotelBookingSystem.util.ResponseStructure;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Hotel>> saveHotel(@RequestBody HotelDto dto, @RequestParam int addressId) {
        return hotelService.saveHotel(dto, addressId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Hotel>> findHotelById(@PathVariable int id) {
        return hotelService.findHotelById(id);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Hotel>>> findAllHotels() {
        return hotelService.findAllHotels();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteHotel(@PathVariable int id) {
        return hotelService.deleteHotel(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Hotel>> updateHotel(@PathVariable int id, @RequestBody HotelDto dto) {
        return hotelService.updateHotel(id, dto);
    }
}