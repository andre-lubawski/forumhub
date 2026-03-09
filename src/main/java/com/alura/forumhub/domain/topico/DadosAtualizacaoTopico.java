package com.alura.forumhub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull(message = "ID é obrigatório")
        Long id,

        String titulo,
        String mensagem,
        EstadoTopico estado
) {}