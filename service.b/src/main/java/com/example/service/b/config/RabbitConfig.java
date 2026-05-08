package com.example.service.b.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public SimpleMessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();

        converter.setAllowedListPatterns(
                java.util.List.of("*")
        );

        return converter;
    }
    @Bean
    public Queue loginQueue() {
        return new Queue("login.queue");
    }

    @Bean
    public Queue loginReplyQueue() {
        return new Queue("login.reply.queue");
    }
    @Bean
    public Queue profileQueue() {
        return new Queue("profile.queue");
    }

    @Bean
    public Queue profileReplyQueue() {
        return new Queue("profile.reply.queue");
    }
}