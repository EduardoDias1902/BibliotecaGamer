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

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final JogadorRepository jogadorRepository;
    private final JogoRepository jogoRepository;

    // ✅ CREATE
    public EmprestimoResponseDTO salvar(EmprestimoRequestDTO dto){

        Jogador jogador = buscarJogador(dto.jogadorId());
        Jogo jogo = buscarJogo(dto.jogoId()); // 🔥 CORREÇÃO AQUI

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setJogador(jogador);
        emprestimo.setJogo(jogo);
        emprestimo.setDataEmprestimo(dto.dataEmprestimo());

        return toDTO(emprestimoRepository.save(emprestimo));
    }

    // ✅ READ (LISTAR)
    public List<EmprestimoResponseDTO> listar(){
        return emprestimoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ✅ READ (POR ID)
    public EmprestimoResponseDTO buscarPorId(Long id){
        Emprestimo e = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        return toDTO(e);
    }

    // ✅ UPDATE
    public EmprestimoResponseDTO atualizar(Long id, EmprestimoRequestDTO dto){

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        Jogador jogador = buscarJogador(dto.jogadorId());
        Jogo jogo = buscarJogo(dto.jogoId());

        emprestimo.setJogador(jogador);
        emprestimo.setJogo(jogo);
        emprestimo.setDataEmprestimo(dto.dataEmprestimo());

        return toDTO(emprestimoRepository.save(emprestimo));
    }

    // ✅ DELETE
    public void deletar(Long id){
        emprestimoRepository.deleteById(id);
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

    private EmprestimoResponseDTO toDTO(Emprestimo e){
        return new EmprestimoResponseDTO(
                e.getId(),
                e.getJogador().getNome(),
                e.getJogo().getTitulo(),
                e.getDataEmprestimo()
        );
    }
}