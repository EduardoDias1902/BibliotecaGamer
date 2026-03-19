package com.exemplo.bibliotecagamer.controller;

import com.exemplo.bibliotecagamer.dto.*;
import com.exemplo.bibliotecagamer.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
@RequiredArgsConstructor

public class JogadorController {

    private final JogadorService service;

    @GetMapping
    public List<JogadorResponseDTO> listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    public JogadorResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }
    @PostMapping
    public JogadorResponseDTO salvar (@RequestBody JogadorRequestDTO dto){
        return service.salvar(dto);
    }
    @PutMapping("/{id}")
    public JogadorResponseDTO atualizar(@PathVariable Long id,
                                        @RequestBody JogadorRequestDTO dto){
        return service.atualizar(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
