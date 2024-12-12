package com.hms.HotelBookingSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.HotelBookingSystem.entity.Address;
import com.hms.HotelBookingSystem.repository.AddressRepository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	public Address updateAddress(int addressId, Address address) {
		Optional<Address> optional=addressRepository.findById(addressId);
		if(optional.isPresent()) {
			address.setAddressId(addressId);
			addressRepository.save(address);
			return optional.get();
		}
		return null;
	}
	public Address findByAddressId(int addressId) {
		Optional<Address> optional=addressRepository.findById(addressId);
		if(optional.isPresent()) 
			return optional.get();
		return null;
	}
	
	public boolean deleteAddress(int addressId) {
		Optional<Address> optional=addressRepository.findById(addressId);
		if(optional.isPresent()) { 
			addressRepository.delete(optional.get());
			return true;
		}
		return false;
	}
	
	public List<Address> fetchAllAddress(){
		return addressRepository.findAll();
	}

}
