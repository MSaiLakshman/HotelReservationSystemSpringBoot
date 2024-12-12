package com.hms.HotelBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.HotelBookingSystem.dto.AddressDto;
import com.hms.HotelBookingSystem.entity.Address;
import com.hms.HotelBookingSystem.service.AddressService;
import com.hms.HotelBookingSystem.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody AddressDto addressDto){
		return addressService.saveAddress(addressDto);
	}
	@GetMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> findByAddressID(@PathVariable int addressId){
		return addressService.findByAddressID(addressId);
	}
	
	
	@PostMapping("/{addressId}")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@PathVariable int addressId, @RequestBody AddressDto addressDto){
		return addressService.updateAddress(addressId, addressDto);
	}
	
	@DeleteMapping("/{addressID}")
    public ResponseEntity<ResponseStructure<String>> deleteAddress(@PathVariable int addressID) {
        return addressService.deleteAddress(addressID);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Address>>> getAllAddress() {
        return addressService.getAllAddresses();
    }
}
