package com.exemplo.bibliotecagamer.service;

import com.exemplo.bibliotecagamer.dto.JogadorRequestDTO;
import com.exemplo.bibliotecagamer.dto.JogadorResponseDTO;
import com.exemplo.bibliotecagamer.entity.Jogador;
import com.exemplo.bibliotecagamer.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository repository;

    // ✅ CREATE
    public JogadorResponseDTO salvar(JogadorRequestDTO dto){

        Jogador jogador = new Jogador();
        jogador.setNome(dto.nome());
        jogador.setEmail(dto.email());

        return toDTO(repository.save(jogador));
    }

    // ✅ READ (LISTAR)
    public List<JogadorResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ✅ READ (POR ID)
    public JogadorResponseDTO buscarPorId(Long id){
        Jogador jogador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        return toDTO(jogador);
    }

    // ✅ UPDATE
    public JogadorResponseDTO atualizar(Long id, JogadorRequestDTO dto){

        Jogador jogador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        jogador.setNome(dto.nome());
        jogador.setEmail(dto.email());

        return toDTO(repository.save(jogador));
    }

    // ✅ DELETE
    public void deletar(Long id){
        repository.deleteById(id);
    }

    // 🔹 MÉTODO AUXILIAR (MAPPER)
    private JogadorResponseDTO toDTO(Jogador jogador){
        return new JogadorResponseDTO(
                jogador.getId(),
                jogador.getNome(),
                jogador.getEmail()
        );
    }
}