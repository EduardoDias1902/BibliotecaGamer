package com.exemplo.bibliotecagamer.repository;

import com.exemplo.bibliotecagamer.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByTituloContaining (String titulo);
    Optional<Jogo> findByIdAndTitulo(Long id, String titulo);
    List<Jogo>findByIdIn(List<Long> ids);

}
