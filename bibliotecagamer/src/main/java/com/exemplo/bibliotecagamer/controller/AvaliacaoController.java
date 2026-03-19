package com.exemplo.bibliotecagamer.controller;

import com.exemplo.bibliotecagamer.dto.*;
import com.exemplo.bibliotecagamer.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService service;

    @GetMapping
    public List<AvaliacaoResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public AvaliacaoResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public AvaliacaoResponseDTO salvar(@RequestBody AvaliacaoRequestDTO dto){
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public AvaliacaoResponseDTO atualizar(@PathVariable Long id,
                                          @RequestBody AvaliacaoRequestDTO dto){
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}