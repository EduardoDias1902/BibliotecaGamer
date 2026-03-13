package com.exemplo.bibliotecagamer.dto;

public record AvaliacaoResponseDTO(
        Long id,
        String nomeJogador,
        String tituloJogador,
        int nota
) {}
