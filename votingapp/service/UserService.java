package com.votingapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votingapp.entity.User;
import com.votingapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public User registerUser(User user) {
        // Check if user already exists with email or phone number
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        Optional<User> existingUserByPhone = userRepository.findByPhoneNo(user.getPhoneNo());

        if (existingUserByEmail.isPresent() || existingUserByPhone.isPresent()) {
            throw new RuntimeException("User with email or phone number already exists");
        }
        
        return userRepository.save(user);
    }
    
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}