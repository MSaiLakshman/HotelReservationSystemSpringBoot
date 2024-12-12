package com.hms.HotelBookingSystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.HotelBookingSystem.dao.HotelDao;
import com.hms.HotelBookingSystem.dao.AddressDao;
import com.hms.HotelBookingSystem.dto.HotelDto;
import com.hms.HotelBookingSystem.entity.Hotel;
import com.hms.HotelBookingSystem.exception.AddressIdNotFoundException;
import com.hms.HotelBookingSystem.exception.NotFoundExceeption;
import com.hms.HotelBookingSystem.entity.Address;
import com.hms.HotelBookingSystem.util.ResponseStructure;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ModelMapper modelMapper;

    
    public ResponseEntity<ResponseStructure<Hotel>> saveHotel(HotelDto dto, int addressId) {
        ResponseStructure<Hotel> structure = new ResponseStructure<>();
    
        Address address = addressDao.findByAddressId(addressId);
        if (address == null) {
            throw new AddressIdNotFoundException("Address ID not found");
        }
        Hotel hotel = modelMapper.map(dto, Hotel.class);
        hotel.setAddress(address); 

        Hotel savedHotel = hotelDao.saveHotel(hotel);
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Hotel saved successfully!");
        structure.setData(savedHotel);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }
    public ResponseEntity<ResponseStructure<Hotel>> findHotelById(int hotelId) {
        ResponseStructure<Hotel> structure = new ResponseStructure<>();
        
        Hotel hotel = hotelDao.findByHotelId(hotelId);
        if (hotel == null) {
           throw new NotFoundExceeption("Hotel ID not found exception");
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Hotel found successfully!");
        structure.setData(hotel);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }


    public ResponseEntity<ResponseStructure<List<Hotel>>> findAllHotels() {
        ResponseStructure<List<Hotel>> structure = new ResponseStructure<>();
        
        List<Hotel> hotels = hotelDao.fetchAllHotels();
        if (hotels.isEmpty()) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("No hotels available");
            structure.setData(hotels);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Hotels retrieved successfully");
        structure.setData(hotels);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<String>> deleteHotel(int hotelId) {
        ResponseStructure<String> structure = new ResponseStructure<>();
        
        Hotel hotel = hotelDao.findByHotelId(hotelId);
        if (hotel == null) {
        	throw new NotFoundExceeption("Hotel ID not found exception");
        }

        hotelDao.deleteHotel(hotelId);
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Hotel deleted successfully");
        structure.setData("Hotel with ID: " + hotelId + " has been deleted");
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    
    public ResponseEntity<ResponseStructure<Hotel>> updateHotel(int hotelId, HotelDto dto) {
        ResponseStructure<Hotel> structure = new ResponseStructure<>();

        Hotel hotel = hotelDao.findByHotelId(hotelId);
        if (hotel == null) {
        	throw new NotFoundExceeption("Hotel ID not found exception");
        }

        modelMapper.map(dto, hotel); 
        Hotel updatedHotel = hotelDao.saveHotel(hotel);

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Hotel updated successfully!");
        structure.setData(updatedHotel);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
}
