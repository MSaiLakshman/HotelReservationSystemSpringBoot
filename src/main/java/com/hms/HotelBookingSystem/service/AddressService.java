package com.hms.HotelBookingSystem.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.HotelBookingSystem.dao.AddressDao;
import com.hms.HotelBookingSystem.dto.AddressDto;
import com.hms.HotelBookingSystem.entity.Address;
import com.hms.HotelBookingSystem.exception.AddressIdNotFoundException;
import com.hms.HotelBookingSystem.util.ResponseStructure;


@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto){
		Address address=modelMapper.map(addressDto, Address.class);
		Address dbAddress=addressDao.saveAddress(address);
		ResponseStructure<Address> responseStructure=new ResponseStructure<Address>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Address Saved");
		responseStructure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Address>> findByAddressID(int addressID){
		Address dbAddress=addressDao.findByAddressId(addressID);
		ResponseStructure<Address> responseStructure=new ResponseStructure<>();
		if(dbAddress!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Address Found");
			responseStructure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		}
		else {
			throw new AddressIdNotFoundException("address id not found");
			
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(int addressId, AddressDto addressDto){
		Address address=modelMapper.map(addressDto, Address.class);
		Address dbAddress=addressDao.updateAddress(addressId, address);
		ResponseStructure<Address> responseStructure=new ResponseStructure<Address>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Address Updated");
		responseStructure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteAddress(int addressId) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        if (addressDao.deleteAddress(addressId)) {
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setMessage("Address Deleted Successfully");
            responseStructure.setData("Address with ID " + addressId + " was deleted.");
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
        	throw new AddressIdNotFoundException("address id not found");
        	
        }
    }

    public ResponseEntity<ResponseStructure<List<Address>>> getAllAddresses() {
        List<Address> addresses = addressDao.fetchAllAddress();
        ResponseStructure<List<Address>> responseStructure = new ResponseStructure<>();
        responseStructure.setStatusCode(HttpStatus.OK.value());
        responseStructure.setMessage("Adresses Retrieved");
        responseStructure.setData(addresses);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
}
