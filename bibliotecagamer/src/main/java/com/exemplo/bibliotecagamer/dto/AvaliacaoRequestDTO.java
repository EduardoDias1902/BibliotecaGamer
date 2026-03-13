package com.exemplo.bibliotecagamer.dto;

public record AvaliacaoRequestDTO(
        Long jogadorId,
        Long jogoId,
        int nota
) {
}
