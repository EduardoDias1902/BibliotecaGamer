package com.exemplo.bibliotecagamer.service;

import com.exemplo.bibliotecagamer.dto.CategoriaRequestDTO;
import com.exemplo.bibliotecagamer.dto.CategoriaResponseDTO;
import com.exemplo.bibliotecagamer.entity.Categoria;
import com.exemplo.bibliotecagamer.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;


    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto){

        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());

        return toDTO(repository.save(categoria));
    }


    public List<CategoriaResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }


    public CategoriaResponseDTO buscarPorId(Long id){
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return toDTO(categoria);
    }


    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto){

        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoria.setNome(dto.nome());

        return toDTO(repository.save(categoria));
    }


    public void deletar(Long id){
        repository.deleteById(id);
    }

    private CategoriaResponseDTO toDTO(Categoria categoria){
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }
    public List<CategoriaResponseDTO> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(c -> new CategoriaResponseDTO(
                        c.getId(),
                        c.getNome()
                )).toList();
    }
    public CategoriaResponseDTO buscarNomeExato(String nome){

        Categoria c = repository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return new CategoriaResponseDTO(
                c.getId(),
                c.getNome()
        );
    }
    public List<CategoriaResponseDTO> buscarVarias(List<Long> ids){

        return repository.findByIdIn(ids)
                .stream()
                .map(c -> new CategoriaResponseDTO(
                        c.getId(),
                        c.getNome()
                ))
                .toList();
    }
}