package com.senai.api.api.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Resposta com paginação de contas")
public class ContaPageResponse {

    @Schema(description = "Conteúdo da página, que são as contas listadas")
    private List<ListarContaResponse> content;

    @Schema(description = "Número da página atual")
    private int pageNumber;

    @Schema(description = "Número de elementos por página")
    private int pageSize;

    @Schema(description = "Número total de páginas")
    private int totalPages;

    @Schema(description = "Total de elementos")
    private long totalElements;
}
