package com.example.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.model.JwtResponse;
import com.example.shopping.model.LoginRequest;
import com.example.shopping.model.user;
import com.example.shopping.repositories.userRepository;
import com.example.shopping.security.JwtUtil;
import com.example.shopping.services.userService;


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {"http://furniture-storefrontend.s3-website-us-east-1.amazonaws.com",
                        "http://localhost:3000",

                    },
             allowCredentials = "true")
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody user user) {
    try {
        user saved = userService.registerUser(user);
        return ResponseEntity.ok(saved);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(
            java.util.Collections.singletonMap("message", e.getMessage())
        );
    }
}
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    user user = userRepository.findByUsername(loginRequest.getUsername());
    if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        org.springframework.security.core.userdetails.User userDetails =
            new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new java.util.ArrayList<>()
            );
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
}


@GetMapping("/profile")
public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    String username = jwtUtil.getUsernameFromToken(token);
    user user = userRepository.findByUsername(username);
    if (user != null) {
        user.setPassword(null); // Don't return password
        return ResponseEntity.ok(user);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
}

@GetMapping("/me")
public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
    }
    String token = authHeader.replace("Bearer ", "");
    String username;
    try {
        username = jwtUtil.getUsernameFromToken(token);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }
    user user = userRepository.findByUsername(username);
    if (user != null) {
        user.setPassword(null); // Hide password
        return ResponseEntity.ok(user);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
}

@GetMapping("/all")
public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userRepository.findAll());
}

@DeleteMapping("/delete-all")
public ResponseEntity<?> deleteAllUsers() {
    userRepository.deleteAll();
    return ResponseEntity.ok("All users deleted");
}


}