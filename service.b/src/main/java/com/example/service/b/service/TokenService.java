package com.example.service.b.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private final StringRedisTemplate redisTemplate;

    public TokenService(
            StringRedisTemplate redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }

    public String generateToken(String username) {

        String token = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set(
                token,
                username,
                1,
                TimeUnit.HOURS
        );

        return token;
    }

    public boolean isValid(String token) {

        return redisTemplate.hasKey(token);
    }

    public String getUsername(String token) {

        return redisTemplate.opsForValue().get(token);
    }
}