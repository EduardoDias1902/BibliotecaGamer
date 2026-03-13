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

        Categoria c = new Categoria();
        c.setNome(dto.nome());

        c = repository.save(c);

        return new CategoriaResponseDTO(
                c.getId(),
                c.getNome()
        );
    }

    public List<CategoriaResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(c -> new CategoriaResponseDTO(
                        c.getId(),
                        c.getNome()
                ))
                .toList();
    }
}
