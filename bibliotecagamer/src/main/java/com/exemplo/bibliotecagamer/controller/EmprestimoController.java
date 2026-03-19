package com.exemplo.bibliotecagamer.controller;
import com.exemplo.bibliotecagamer.dto.*;
import com.exemplo.bibliotecagamer.service.EmprestimoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor

public class EmprestimoController {
    private final EmprestimoService service;

    @GetMapping
    public List<EmprestimoResponseDTO> listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    public EmprestimoResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }
    @PostMapping
    public EmprestimoResponseDTO salvar(@RequestBody EmprestimoRequestDTO dto){
        return service.salvar(dto);
    }
    @PutMapping("/{id}")
    public EmprestimoResponseDTO atualizar(@PathVariable Long id,
                                           @RequestBody EmprestimoRequestDTO dto) {
        return service.atualizar(id, dto);
    }
}
