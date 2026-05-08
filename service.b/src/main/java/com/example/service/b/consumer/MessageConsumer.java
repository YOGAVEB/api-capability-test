package com.example.service.b.consumer;

import com.example.service.a.dto.LoginRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.example.service.a.dto.LoginResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class MessageConsumer {
    private final RabbitTemplate rabbitTemplate;

    public MessageConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "login.queue")
    public void receiveLogin(LoginRequest request) {

        System.out.println("Login Request Received");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());

        LoginResponse response;

        if ("admin".equals(request.getUsername())
                && "123".equals(request.getPassword())) {

            System.out.println("LOGIN SUCCESS");

            response = new LoginResponse(
                    true,
                    "Login success",
                    "abc123token"
            );

        } else {

            System.out.println("LOGIN FAILED");

            response = new LoginResponse(
                    false,
                    "Invalid credentials",
                    null
            );
        }

        rabbitTemplate.convertAndSend(
                "login.reply.queue",
                response
        );
    }
}