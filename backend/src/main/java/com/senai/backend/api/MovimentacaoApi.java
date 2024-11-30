package com.senai.backend.api;

import com.senai.backend.api.request.CriarMovimentacaoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/senai-backend/v1/movimentacao")
public interface MovimentacaoApi {

    @PostMapping
    @Operation(summary = "Cria uma movimentação", responses = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT")
    })
    ResponseEntity<Void> salvarMovimentacao(@RequestBody CriarMovimentacaoRequest request);
}
