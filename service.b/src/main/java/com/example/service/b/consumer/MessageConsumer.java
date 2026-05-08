package com.example.service.b.consumer;

import com.example.service.a.dto.LoginRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @RabbitListener(queues = "test.queue")
    public void receiveMessage(String message) {

        System.out.println("Message received from RabbitMQ:");
        System.out.println(message);
    }
    @RabbitListener(queues = "login.queue")
    public void receiveLogin(LoginRequest request) {

        System.out.println("Login Request Received");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());

        if(request.getUsername().equals("admin")
                && request.getPassword().equals("123")) {

            System.out.println("LOGIN SUCCESS");
        } else {
            System.out.println("LOGIN FAILED");
        }
    }
}