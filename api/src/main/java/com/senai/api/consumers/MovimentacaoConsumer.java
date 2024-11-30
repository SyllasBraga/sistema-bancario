package com.senai.api.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.configurations.RabbitMQConfig;
import com.senai.api.services.MovimentacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoConsumer {

    private final MovimentacaoService movimentacaoService;

    private final ObjectMapper objectMapper;

    public MovimentacaoConsumer(MovimentacaoService movimentacaoService, ObjectMapper objectMapper) {
        this.movimentacaoService = movimentacaoService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeMessage(String message) {
        System.out.println(message);
        try {
            CriarMovimentacaoRequest request = objectMapper.readValue(message, CriarMovimentacaoRequest.class);
            movimentacaoService.criarMovimentacao(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
