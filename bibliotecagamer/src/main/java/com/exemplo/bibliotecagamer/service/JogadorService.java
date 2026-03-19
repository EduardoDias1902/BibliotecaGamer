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

        Jogador jogador = new Jogador();
        jogador.setNome(dto.nome());
        jogador.setEmail(dto.email());

        return toDTO(repository.save(jogador));
    }


    public List<JogadorResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }


    public JogadorResponseDTO buscarPorId(Long id){
        Jogador jogador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        return toDTO(jogador);
    }


    public JogadorResponseDTO atualizar(Long id, JogadorRequestDTO dto){

        Jogador jogador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        jogador.setNome(dto.nome());
        jogador.setEmail(dto.email());

        return toDTO(repository.save(jogador));
    }


    public void deletar(Long id){
        repository.deleteById(id);
    }


    private JogadorResponseDTO toDTO(Jogador jogador){
        return new JogadorResponseDTO(
                jogador.getId(),
                jogador.getNome(),
                jogador.getEmail()
        );
    }
    public List<JogadorResponseDTO> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(j -> new JogadorResponseDTO(
                        j.getId(),
                        j.getNome(),
                        j.getEmail()
                )).toList();
    }
    public JogadorResponseDTO buscarPorEmail(String email){

        Jogador j = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        return new JogadorResponseDTO(
                j.getId(),
                j.getNome(),
                j.getEmail()
        );
    }
    public List<JogadorResponseDTO> buscarVarios(List<Long> ids){

        return repository.findByIdIn(ids)
                .stream()
                .map(j -> new JogadorResponseDTO(
                        j.getId(),
                        j.getNome(),
                        j.getEmail()
                ))
                .toList();
    }
    public JogadorResponseDTO buscarPorIdENome(Long id, String nome){

        Jogador j = repository.findByIdAndNome(id, nome)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        return new JogadorResponseDTO(
                j.getId(),
                j.getNome(),
                j.getEmail()
        );
    }
}