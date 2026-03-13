package com.exemplo.bibliotecagamer.repository;

import com.exemplo.bibliotecagamer.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador,Long > {
}
