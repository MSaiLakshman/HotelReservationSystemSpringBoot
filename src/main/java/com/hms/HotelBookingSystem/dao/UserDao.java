package com.hms.HotelBookingSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.HotelBookingSystem.entity.User;
import com.hms.HotelBookingSystem.repository.UserRepository;
@Repository
public class UserDao {
	@Autowired	
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateRoom(int userID, User user )
	{
		Optional<User> optional=userRepository.findById(userID);
		if(optional.isPresent()) {
			user.setUserId(userID);
			userRepository.save(user);
			return optional.get();
		}
		return null;
	}
	public User findByUserId(int roomID) {
		Optional<User> optional=userRepository.findById(roomID);
		if(optional.isPresent()) 
			return optional.get();
		return null;
	}
	
	public boolean deleteUser(int userID) {
		Optional<User> optional=userRepository.findById(userID);
		if(optional.isPresent()) { 
			userRepository.delete(optional.get());
			return true;
		}
		return false;
	}
	
	public List<User> fetchAllUsers(){
		return userRepository.findAll();
	}

}
