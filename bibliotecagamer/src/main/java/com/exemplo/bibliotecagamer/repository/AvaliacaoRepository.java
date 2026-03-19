package com.exemplo.bibliotecagamer.repository;

import com.exemplo.bibliotecagamer.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByJogadorId(Long jogadorId);

    List<Avaliacao> findByJogoId(Long jogoId);

    List<Avaliacao> findByNotaGreaterThanEqual(int nota);

    List<Avaliacao> findByJogadorIdAndNotaGreaterThanEqual(Long jogadorId, int nota);
}

