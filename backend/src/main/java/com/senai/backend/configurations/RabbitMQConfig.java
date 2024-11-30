package com.senai.backend.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.name}")
    public static final String QUEUE_NAME = "movimentacaoQueue";
    @Bean
    public Queue movimentacaoQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}
