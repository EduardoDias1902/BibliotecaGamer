package com.exemplo.bibliotecagamer.service;

import com.exemplo.bibliotecagamer.dto.EmprestimoRequestDTO;
import com.exemplo.bibliotecagamer.dto.EmprestimoResponseDTO;
import com.exemplo.bibliotecagamer.entity.Emprestimo;
import com.exemplo.bibliotecagamer.entity.Jogador;
import com.exemplo.bibliotecagamer.entity.Jogo;
import com.exemplo.bibliotecagamer.repository.EmprestimoRepository;
import com.exemplo.bibliotecagamer.repository.JogadorRepository;
import com.exemplo.bibliotecagamer.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final JogadorRepository jogadorRepository;
    private final JogoRepository jogoRepository;

    public EmprestimoResponseDTO salvar(EmprestimoRequestDTO dto){
        Jogador jogador = jogadorRepository.findById(dto.jogadorId()).orElseThrow();
        Jogo jogo = jogoRepository.findById(dto.jogadorId()).orElseThrow();

        Emprestimo e = new Emprestimo();
        e.setJogador(jogador);
        e.setJogo(jogo);
        e.setDataEmprestimo(dto.dataEmprestimo());

        e= emprestimoRepository.save(e);

        return new EmprestimoResponseDTO(
                e.getId(),
                jogador.getNome(),
                jogo.getTitulo(),
                e.getDataEmprestimo()
        );
    }
}
