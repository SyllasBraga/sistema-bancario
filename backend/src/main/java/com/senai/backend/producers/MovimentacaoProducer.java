package com.senai.backend.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.backend.api.request.CriarMovimentacaoRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoProducer {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public MovimentacaoProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String queueName, CriarMovimentacaoRequest message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(queueName, jsonMessage);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar mensagem para JSON", e);
        }
    }
}
