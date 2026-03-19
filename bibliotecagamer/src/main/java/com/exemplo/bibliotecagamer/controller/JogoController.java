package com.exemplo.bibliotecagamer.controller;

import com.exemplo.bibliotecagamer.dto.*;
import com.exemplo.bibliotecagamer.service.JogadorService;
import com.exemplo.bibliotecagamer.service.JogoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/jogos")
public class JogoController {
    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping
    public List<JogoResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public JogoResponseDTO salvar(@RequestBody JogoRequestDTO dto){
        return service.salvar(dto);
    }
    @PutMapping("/{id}")
    public JogoResponseDTO atualizar (@PathVariable Long id,
                                      @RequestBody JogoRequestDTO dto){
        return service.atualizar(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id){
        service.deletar(id);
    }

    @GetMapping("/buscar")
    public List<JogoResponseDTO> buscar(@RequestParam String titulo){
        return service.buscarPorTitulo(titulo);
    }

    @PostMapping("/buscar-varios")
    public List<JogoResponseDTO> buscarVarios(@RequestBody List<Long> ids){
        return service.buscarVarios(ids);
    }
}
