package com.example.service.a.controller;

import com.example.service.a.producer.MessageProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MessageProducer producer;

    public TestController(MessageProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/test")
    public String test() {

        producer.sendMessage("Hello from Service A");

        return "Message sent!";
    }
}