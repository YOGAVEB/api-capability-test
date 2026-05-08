package com.example.service.a.controller;

import com.example.service.a.dto.LoginRequest;
import com.example.service.a.producer.MessageProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final MessageProducer producer;

    public LoginController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        producer.sendLogin(request);

        return "Login request sent!";
    }
}