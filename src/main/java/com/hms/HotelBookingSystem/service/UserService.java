package com.hms.HotelBookingSystem.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.HotelBookingSystem.dao.AddressDao;
import com.hms.HotelBookingSystem.dao.UserDao;
import com.hms.HotelBookingSystem.dto.UserDto;
import com.hms.HotelBookingSystem.entity.Address;

import com.hms.HotelBookingSystem.entity.User;
import com.hms.HotelBookingSystem.exception.AddressIdNotFoundException;
import com.hms.HotelBookingSystem.exception.NotFoundExceeption;
import com.hms.HotelBookingSystem.util.ResponseStructure;
@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressDao addressDao;

    public ResponseEntity<ResponseStructure<User>> saveUser(UserDto dto, int addressId) {
        ResponseStructure<User> structure = new ResponseStructure<>();

        Address address = addressDao.findByAddressId(addressId);
        if (address == null) {
        	throw new AddressIdNotFoundException("Address ID not found Exception");
        }
        User user = modelMapper.map(dto, User.class);
        user.setAddress(address);

        User savedUser = userDao.saveUser(user);
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("User saved successfully!");
        structure.setData(savedUser);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<User>> findUserById(int userId) {
        ResponseStructure<User> structure = new ResponseStructure<>();

        User user = userDao.findByUserId(userId);
        if (user == null) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("User not found with ID: " + userId);
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("User found successfully!");
        structure.setData(user);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<User>> updateUser(int userId, UserDto dto) {
        ResponseStructure<User> structure = new ResponseStructure<>();

        User user = userDao.findByUserId(userId);
        if (user == null) {
        	throw new NotFoundExceeption("User ID not found exception");
        }

        modelMapper.map(dto, user);
        User updatedUser = userDao.saveUser(user);

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("User updated successfully!");
        structure.setData(updatedUser);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<String>> deleteUser(int userId) {
        ResponseStructure<String> structure = new ResponseStructure<>();

        User user = userDao.findByUserId(userId);
        if (user == null) {
        	throw new NotFoundExceeption("User ID not found exception");
        }

        userDao.deleteUser(userId);
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("User deleted successfully!");
        structure.setData("User with ID: " + userId + " has been deleted.");
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
        ResponseStructure<List<User>> structure = new ResponseStructure<>();

        List<User> users = userDao.fetchAllUsers();
        if (users.isEmpty()) {
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setMessage("No users found.");
            structure.setData(users);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }

        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Users retrieved successfully!");
        structure.setData(users);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
}