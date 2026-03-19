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


    public EmprestimoResponseDTO salvar(EmprestimoRequestDTO dto){

        Jogador jogador = buscarJogador(dto.jogadorId());
        Jogo jogo = buscarJogo(dto.jogoId()); // 🔥 CORREÇÃO AQUI

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setJogador(jogador);
        emprestimo.setJogo(jogo);
        emprestimo.setDataEmprestimo(dto.dataEmprestimo());

        return toDTO(emprestimoRepository.save(emprestimo));
    }


    public List<EmprestimoResponseDTO> listar(){
        return emprestimoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }


    public EmprestimoResponseDTO buscarPorId(Long id){
        Emprestimo e = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        return toDTO(e);
    }


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


    public void deletar(Long id){
        emprestimoRepository.deleteById(id);
    }



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
    public List<EmprestimoResponseDTO> buscarPorJogador(Long jogadorId){

        return emprestimoRepository.findByJogadorId(jogadorId)
                .stream()
                .map(e -> new EmprestimoResponseDTO(
                        e.getId(),
                        e.getJogador().getNome(),
                        e.getJogo().getTitulo(),
                        e.getDataEmprestimo()
                )).toList();
    }
    public List<EmprestimoResponseDTO> buscarPorJogo(Long jogoId){

        return emprestimoRepository.findByJogoId(jogoId)
                .stream()
                .map(e -> new EmprestimoResponseDTO(
                        e.getId(),
                        e.getJogador().getNome(),
                        e.getJogo().getTitulo(),
                        e.getDataEmprestimo()
                ))
                .toList();
    }
    public List<EmprestimoResponseDTO> buscarPorJogadorEJogo(Long jogadorId, Long jogoId){

        return emprestimoRepository.findByJogadorIdAndJogoId(jogadorId, jogoId)
                .stream()
                .map(e -> new EmprestimoResponseDTO(
                        e.getId(),
                        e.getJogador().getNome(),
                        e.getJogo().getTitulo(),
                        e.getDataEmprestimo()
                ))
                .toList();
    }
}