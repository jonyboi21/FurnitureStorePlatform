package com.example.shopping.controllers;
import org.springframework.web.bind.annotation.*;


@RestController
public class HealthController {
    @GetMapping("/health")
    public String home() {
        return "OK";
    }
}
