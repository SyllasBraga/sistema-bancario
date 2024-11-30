package com.senai.backend.controllers;

import com.senai.backend.api.MovimentacaoApi;
import com.senai.backend.api.request.CriarMovimentacaoRequest;
import com.senai.backend.configurations.RabbitMQConfig;
import com.senai.backend.producers.MovimentacaoProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovimentacaoController implements MovimentacaoApi {

    private final MovimentacaoProducer producer;

    public MovimentacaoController(MovimentacaoProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Void> salvarMovimentacao(CriarMovimentacaoRequest movimentacao) {
        producer.sendMessage(RabbitMQConfig.QUEUE_NAME, movimentacao);
        return ResponseEntity.noContent().build();
    }
}
