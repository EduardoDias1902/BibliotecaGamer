package com.exemplo.bibliotecagamer.service;

import com.exemplo.bibliotecagamer.dto.AvaliacaoRequestDTO;
import com.exemplo.bibliotecagamer.dto.AvaliacaoResponseDTO;
import com.exemplo.bibliotecagamer.entity.Avaliacao;
import com.exemplo.bibliotecagamer.entity.Jogador;
import com.exemplo.bibliotecagamer.entity.Jogo;
import com.exemplo.bibliotecagamer.repository.AvaliacaoRepository;
import com.exemplo.bibliotecagamer.repository.JogadorRepository;
import com.exemplo.bibliotecagamer.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final JogadorRepository jogadorRepository;
    private final JogoRepository jogoRepository;

    // ✅ CREATE
    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto){

        Jogador jogador = buscarJogador(dto.jogadorId());
        Jogo jogo = buscarJogo(dto.jogoId());

        // 🔥 validação simples (importante)
        if (dto.nota() < 0 || dto.nota() > 10) {
            throw new RuntimeException("Nota deve estar entre 0 e 10");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setJogador(jogador);
        avaliacao.setJogo(jogo);
        avaliacao.setNota(dto.nota());

        return toDTO(avaliacaoRepository.save(avaliacao));
    }

    // ✅ READ (LISTAR)
    public List<AvaliacaoResponseDTO> listar(){
        return avaliacaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ✅ READ (POR ID)
    public AvaliacaoResponseDTO buscarPorId(Long id){
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        return toDTO(avaliacao);
    }

    // ✅ UPDATE
    public AvaliacaoResponseDTO atualizar(Long id, AvaliacaoRequestDTO dto){

        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        Jogador jogador = buscarJogador(dto.jogadorId());
        Jogo jogo = buscarJogo(dto.jogoId());

        if (dto.nota() < 0 || dto.nota() > 10) {
            throw new RuntimeException("Nota deve estar entre 0 e 10");
        }

        avaliacao.setJogador(jogador);
        avaliacao.setJogo(jogo);
        avaliacao.setNota(dto.nota());

        return toDTO(avaliacaoRepository.save(avaliacao));
    }

    // ✅ DELETE
    public void deletar(Long id){
        avaliacaoRepository.deleteById(id);
    }

    // 🔹 MÉTODOS AUXILIARES

    private Jogador buscarJogador(Long id){
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
    }

    private Jogo buscarJogo(Long id){
        return jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
    }

    private AvaliacaoResponseDTO toDTO(Avaliacao a){
        return new AvaliacaoResponseDTO(
                a.getId(),
                a.getJogador().getNome(),
                a.getJogo().getTitulo(),
                a.getNota()
        );
    }
}