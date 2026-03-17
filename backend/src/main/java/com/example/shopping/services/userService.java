package com.example.shopping.services;


import com.example.shopping.model.user;
import com.example.shopping.repositories.userRepository;
import org.springframework.stereotype.Service;
import com.example.shopping.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class userService {
    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Make sure to configure this bean

    public user registerUser(user user) throws Exception {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
