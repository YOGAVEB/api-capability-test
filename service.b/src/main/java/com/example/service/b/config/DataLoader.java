package com.example.service.b.config;

import com.example.service.b.entity.UserEntity;
import com.example.service.b.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            UserRepository userRepository
    ) {

        return args -> {

            if (userRepository.findByUsername("admin") == null) {

                UserEntity user =
                        new UserEntity(
                                "admin",
                                "123"
                        );

                userRepository.save(user);

                System.out.println("DEFAULT USER CREATED");
            }
        };
    }
}