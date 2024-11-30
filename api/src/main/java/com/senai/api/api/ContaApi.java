package com.senai.api.api;

import java.util.List;

import com.senai.api.api.responses.RespostaGenerica;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senai.api.api.requests.CriarContaRequest;
import com.senai.api.api.responses.ContaPageResponse;
import com.senai.api.api.responses.CriarContaResponse;
import com.senai.api.api.responses.ListarContaResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.websocket.server.PathParam;

@RequestMapping("/senai-api/v1/conta")
public interface ContaApi {

    @PostMapping
    @Operation(summary = "Cria uma conta", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CriarContaResponse.class))
            })
    })
    ResponseEntity<CriarContaResponse> criarConta(@Valid @RequestBody CriarContaRequest request);

    @GetMapping
    @Operation(summary = "Listar todas as contas", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ContaPageResponse.class))
            })
    })
    ResponseEntity<Page<ListarContaResponse>> listarConta(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size);

    @GetMapping(value = "/{cpf}")
    @Operation(summary = "Listar todas as contas por CPF", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ListarContaResponse.class))
            })
    })
    ResponseEntity<List<ListarContaResponse>> listarContaPorCpf(
            @Valid @PathVariable(value = "cpf") @CPF String cpf);

    @DeleteMapping(value = "/{conta}")
    @Operation(summary = "Excluir uma conta", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RespostaGenerica.class))
            })
    })
    ResponseEntity<RespostaGenerica> deletarConta(@PathVariable(value = "conta") String conta);
}
