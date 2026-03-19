package com.exemplo.bibliotecagamer.service;

import com.exemplo.bibliotecagamer.dto.JogoRequestDTO;
import com.exemplo.bibliotecagamer.dto.JogoResponseDTO;
import com.exemplo.bibliotecagamer.entity.Categoria;
import com.exemplo.bibliotecagamer.entity.Jogo;
import com.exemplo.bibliotecagamer.repository.CategoriaRepository;
import com.exemplo.bibliotecagamer.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;

    // ✅ CREATE
    public JogoResponseDTO salvar(JogoRequestDTO dto){

        Categoria categoria = buscarCategoria(dto.categoriaId());

        Jogo jogo = new Jogo();
        jogo.setTitulo(dto.titulo());
        jogo.setPlataforma(dto.plataforma());
        jogo.setCategoria(categoria);

        return toDTO(jogoRepository.save(jogo));
    }

    // ✅ READ (LISTAR)
    public List<JogoResponseDTO> listar(){
        return jogoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public JogoResponseDTO buscarPorId(Long id){
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        return toDTO(jogo);
    }

    public JogoResponseDTO atualizar(Long id, JogoRequestDTO dto){

        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        Categoria categoria = buscarCategoria(dto.categoriaId());

        jogo.setTitulo(dto.titulo());
        jogo.setPlataforma(dto.plataforma());
        jogo.setCategoria(categoria);

        return toDTO(jogoRepository.save(jogo));
    }


    public void deletar(Long id){
        jogoRepository.deleteById(id);
    }


    private Categoria buscarCategoria(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }


    private JogoResponseDTO toDTO(Jogo jogo){
        return new JogoResponseDTO(
                jogo.getId(),
                jogo.getTitulo(),
                jogo.getPlataforma(),
                jogo.getCategoria().getNome()
        );
    }
}