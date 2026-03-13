package com.exemplo.bibliotecagamer.dto;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        Long jogadorId,
        Long jogoId,
        LocalDate dataEmprestimo
) {
}
