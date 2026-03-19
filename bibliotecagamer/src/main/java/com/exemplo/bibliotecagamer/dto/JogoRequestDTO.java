package com.exemplo.bibliotecagamer.dto;

public record JogoRequestDTO(
        String titulo,
        String plataforma,
        Long categoriaId
) {}