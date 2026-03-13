package com.exemplo.bibliotecagamer.service;

import com.exemplo.bibliotecagamer.dto.JogoRequestDTO;
import com.exemplo.bibliotecagamer.dto.JogoResponseDTO;
import com.exemplo.bibliotecagamer.entity.Categoria;
import com.exemplo.bibliotecagamer.entity.Jogo;
import com.exemplo.bibliotecagamer.repository.CategoriaRepository;
import com.exemplo.bibliotecagamer.repository.JogoRepository;
import org.springframework.stereotype.Service;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;

    public JogoService(JogoRepository jogoRepository, CategoriaRepository categoriaRepository) {
        this.jogoRepository = jogoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public JogoResponseDTO salvar(JogoRequestDTO dto){

        Categoria categoria = categoriaRepository
                .findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Jogo jogo = new Jogo();
        jogo.setTitulo(dto.titulo());
        jogo.setPlataforma(dto.plataforma());
        jogo.setCategoria(categoria);

        jogo = jogoRepository.save(jogo);

        return new JogoResponseDTO(
                jogo.getId(),
                jogo.getTitulo(),
                jogo.getPlataforma(),
                categoria.getNome()
        );
    }
}