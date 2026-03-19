package com.exemplo.bibliotecagamer.controller;
import com.exemplo.bibliotecagamer.dto.*;
import com.exemplo.bibliotecagamer.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping
    public List<CategoriaResponseDTO>Listar(){
        return service.listar();
    }
    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }
    @PostMapping
    public CategoriaResponseDTO salvar(@RequestBody CategoriaRequestDTO dto){
        return service.salvar(dto);
    }
    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id,
                                          @RequestBody CategoriaRequestDTO dto){
        return service.atualizar(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id){
        service.deletar(id);
    }
}
