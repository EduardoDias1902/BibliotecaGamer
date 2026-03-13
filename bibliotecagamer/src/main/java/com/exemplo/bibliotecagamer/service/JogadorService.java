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

    public JogadorResponseDTO salvar(JogadorRequestDTO dto){

        Jogador j = new Jogador();
        j.setNome(dto.nome());
        j.setEmail(dto.email());

        j = repository.save(j);

        return new JogadorResponseDTO(
                j.getId(),
                j.getNome(),
                j.getEmail()
        );
    }

    public List<JogadorResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(j -> new JogadorResponseDTO(
                        j.getId(),
                        j.getNome(),
                        j.getEmail()
                ))
                .toList();
    }
}