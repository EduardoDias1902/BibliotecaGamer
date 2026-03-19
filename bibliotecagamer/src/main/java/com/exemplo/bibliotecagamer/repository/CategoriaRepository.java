package com.exemplo.bibliotecagamer.repository;

import com.exemplo.bibliotecagamer.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNomeContainingIgnoreCase(String nome);
    Optional<Categoria> findByNome (String nome);
    List<Categoria> findByIdIn(List<Long> ids);
}
