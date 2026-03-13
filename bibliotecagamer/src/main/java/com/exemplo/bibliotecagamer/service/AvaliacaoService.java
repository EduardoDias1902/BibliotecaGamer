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

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final JogadorRepository jogadorRepository;
    private final JogoRepository jogoRepository;

    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto){
        Jogador jogador = jogadorRepository.findById(dto.jogadorId()).orElseThrow();
        Jogo jogo = jogoRepository.findById(dto.jogoId()).orElseThrow();

        Avaliacao a = new Avaliacao();
        a.setJogador(jogador);
        a.setJogo(jogo);
        a.setNota(dto.nota());

        a = avaliacaoRepository.save(a);

        return new AvaliacaoResponseDTO(
                a.getId(),
                jogador.getNome(),
                jogo.getTitulo(),
                a.getNota()
        );
    }
}
