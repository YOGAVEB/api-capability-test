package com.example.service.b.consumer;

import com.example.service.a.dto.LoginRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.example.service.a.dto.LoginResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.example.service.b.entity.UserEntity;
import com.example.service.b.repository.UserRepository;
import com.example.service.b.service.TokenService;

@Service
public class MessageConsumer {
    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public MessageConsumer(
            RabbitTemplate rabbitTemplate,
            UserRepository userRepository,
            TokenService tokenService
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @RabbitListener(queues = "login.queue")
    public void receiveLogin(LoginRequest request) {

        System.out.println("Login Request Received");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());

        UserEntity user = userRepository.findByUsername(
                request.getUsername()
        );

        LoginResponse response;

        if (user != null &&
                user.getPassword().equals(
                        request.getPassword()
                )) {

            System.out.println("LOGIN SUCCESS");

            response = new LoginResponse(
                    true,
                    "Login success",
                    tokenService.generateToken(
                            user.getUsername()
                    )
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