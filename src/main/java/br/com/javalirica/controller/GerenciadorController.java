package br.com.javalirica.controller;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.dto.GerenciadorBaseDTO;
import br.com.javalirica.service.GerenciadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/gerenciador")
public class GerenciadorController {

    private final GerenciadorService gerenciadorService;

    public GerenciadorController(GerenciadorService gerenciadorService) {
        this.gerenciadorService = gerenciadorService;
    }

    @PostMapping
    public ResponseEntity<?> criarGerenciador (@RequestBody GerenciadorBaseDTO novoGerenciador){
       GerenciadorBase gerenciadorBaseSalvo = gerenciadorService.criarGerenciador(novoGerenciador);
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciadorBaseSalvo);
    }
}
