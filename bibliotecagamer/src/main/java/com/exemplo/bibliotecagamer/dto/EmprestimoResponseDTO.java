package com.exemplo.bibliotecagamer.dto;

import java.time.LocalDate;

public record EmprestimoResponseDTO (
        Long id,
        String nomeJogador,
        String tituloJogo,
        LocalDate dataEmprestimo
) {
}
