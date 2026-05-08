package com.example.service.a.producer;

import com.example.service.a.dto.LoginRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.example.service.a.dto.LoginResponse;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("test.queue", message);
    }

    public LoginResponse sendLogin(LoginRequest request) {

        rabbitTemplate.convertAndSend(
                "login.queue",
                request
        );

        Object response = rabbitTemplate.receiveAndConvert(
                "login.reply.queue",
                5000
        );

        return (LoginResponse) response;
    }
}