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

    // ✅ CREATE
    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto){

        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());

        return toDTO(repository.save(categoria));
    }

    // ✅ READ (LISTAR)
    public List<CategoriaResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ✅ READ (POR ID)
    public CategoriaResponseDTO buscarPorId(Long id){
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return toDTO(categoria);
    }

    // ✅ UPDATE
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto){

        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoria.setNome(dto.nome());

        return toDTO(repository.save(categoria));
    }

    // ✅ DELETE
    public void deletar(Long id){
        repository.deleteById(id);
    }

    // 🔹 MÉTODO AUXILIAR (MAPPER)
    private CategoriaResponseDTO toDTO(Categoria categoria){
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }
}