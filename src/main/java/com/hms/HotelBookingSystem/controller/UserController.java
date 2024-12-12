package com.hms.HotelBookingSystem.controller;

import com.hms.HotelBookingSystem.dto.UserDto;
import com.hms.HotelBookingSystem.entity.User;
import com.hms.HotelBookingSystem.service.UserService;
import com.hms.HotelBookingSystem.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add/{addressId}")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody UserDto userDto, @PathVariable int addressId) {
        return userService.saveUser(userDto, addressId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
        return userService.findAllUsers();
    }
}
