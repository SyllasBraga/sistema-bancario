package com.senai.backend.api;

import com.senai.backend.api.request.AtualizarPessoaRequest;
import com.senai.backend.api.request.CriarPessoaRequest;
import com.senai.backend.api.response.PessoaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/senai-backend/v1/pessoa")
public interface PessoaApi {

    @PostMapping
    @Operation(summary = "Cria uma pessoa", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaResponse.class))
            })
    })
    ResponseEntity<PessoaResponse> criarPessoa(@Valid @RequestBody CriarPessoaRequest request);

    @GetMapping("/lista")
    @Operation(summary = "Lista pessoas", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaResponse.class))
            })
    })
    ResponseEntity<Page<PessoaResponse>> listarPessoas(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size);

    @PutMapping("/{idPessoa}")
    @Operation(summary = "Atualiza uma pessoa", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PessoaResponse.class))
            })
    })
    ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable Integer idPessoa, @Valid @RequestBody AtualizarPessoaRequest request);

    @DeleteMapping("/{idPessoa}")
    @Operation(summary = "Deleta uma pessoa", responses = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT")
    })
    ResponseEntity<Void> deletarPessoa(@PathVariable Integer idPessoa);
}
