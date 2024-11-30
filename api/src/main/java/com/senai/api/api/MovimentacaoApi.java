package com.senai.api.api;

import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/senai-api/v1/movimentacao")
public interface MovimentacaoApi {

    @PostMapping
    @Operation(summary = "Cria uma movimentação", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CriarMovimentacaoResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content)
    })
    ResponseEntity<CriarMovimentacaoResponse> criarMovimentacao(@Valid @RequestBody CriarMovimentacaoRequest request);
}
