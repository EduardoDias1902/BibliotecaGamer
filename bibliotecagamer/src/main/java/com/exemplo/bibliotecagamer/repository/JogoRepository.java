package com.exemplo.bibliotecagamer.repository;

import com.exemplo.bibliotecagamer.entity.Jogador;
import com.exemplo.bibliotecagamer.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
