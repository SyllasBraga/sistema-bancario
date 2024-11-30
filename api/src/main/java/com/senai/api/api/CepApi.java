package com.senai.api.api;

import com.senai.api.api.requests.CriarCepRequest;
import com.senai.api.api.responses.CriarCepResponse;
import com.senai.api.api.responses.ListarCepResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/senai-api/v1/cep")
public interface CepApi {

    @PostMapping
    @Operation(summary = "Cria um CEP", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CriarCepResponse.class))
            })
    })
    ResponseEntity<CriarCepResponse> criarCep(@Valid @RequestBody CriarCepRequest request);

    @GetMapping("/{cep}")
    @Operation(summary = "Busca um CEP", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ListarCepResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
    })
    ResponseEntity<ListarCepResponse> buscarCep(@PathVariable String cep);
}
