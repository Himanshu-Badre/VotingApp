package com.votingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.votingapp.entity.User;
import com.votingapp.repository.UserRepository;
import com.votingapp.service.UserService;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//
//        if (userService.authenticateUser(username, password)) {
//            return ResponseEntity.ok("Login successful");
//            
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
    	Map<String, String> body = new HashMap<>();
    	
        Optional<User> existingUserByEmail = userRepository.findByEmail(newUser.getEmail());
        Optional<User> existingUserByPhone = userRepository.findByPhoneNo(newUser.getPhoneNo());
        
        body.put("isUserExist", "true");
        if (existingUserByEmail.isPresent() || existingUserByPhone.isPresent()) {
        	return new ResponseEntity<>(body, HttpStatus.OK);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        } else {
            User savedUser = userService.registerUser(newUser);
            return ResponseEntity.ok(savedUser);
        }
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Optional<User> dbUser = userService.findByUsername(user.getUsername());

        if (!dbUser.isPresent() || !dbUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().body(Map.of("message", "Invalid username or password"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("role", dbUser.get().getRole());
        return ResponseEntity.ok(response);
    }
}
