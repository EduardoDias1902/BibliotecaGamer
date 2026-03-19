package com.exemplo.bibliotecagamer.repository;

import com.exemplo.bibliotecagamer.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador,Long > {
    List<Jogador> findByNomeContainingIgnoreCase(String nome);
    Optional<Jogador>findByEmail(String email);
    Optional<Jogador> findByIdAndNome(Long id,String nome);
    List<Jogador> findByIdIn(List<Long> ids);
}
