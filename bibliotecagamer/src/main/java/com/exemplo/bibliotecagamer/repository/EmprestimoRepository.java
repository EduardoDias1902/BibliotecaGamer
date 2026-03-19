package com.exemplo.bibliotecagamer.repository;

import com.exemplo.bibliotecagamer.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByJogadorId(Long jogadorId);
    List<Emprestimo> findByJogoId(Long jogoId);
    List<Emprestimo>findByJogadorIdAndJogoId(Long jogadorId,Long jogoId);
}
