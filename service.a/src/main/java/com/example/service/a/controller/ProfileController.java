package com.example.service.a.controller;

import com.example.service.a.dto.ProfileResponse;
import com.example.service.a.producer.MessageProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final MessageProducer producer;

    public ProfileController(
            MessageProducer producer
    ) {
        this.producer = producer;
    }

    @GetMapping("/profile")
    public ProfileResponse profile(
            @RequestHeader("Authorization")
            String token
    ) {

        return producer.getProfile(token);
    }
}