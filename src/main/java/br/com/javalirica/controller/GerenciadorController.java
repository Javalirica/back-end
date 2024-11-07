package br.com.javalirica.controller;

import br.com.javalirica.domain.AdminGerenciador;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.dto.GerenciadorBaseDTO;
import br.com.javalirica.enums.Roles;
import br.com.javalirica.security.jtw.JwtUtils;
import br.com.javalirica.service.GerenciadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/gerenciador")
public class GerenciadorController {

    private final GerenciadorService gerenciadorService;
    private final JwtUtils jwtUtils;

    public GerenciadorController(GerenciadorService gerenciadorService, JwtUtils jwtUtils) {
        this.gerenciadorService = gerenciadorService;
		this.jwtUtils = jwtUtils;
	}

    @PostMapping("/primeiro")
    public ResponseEntity<?>primeiroAcesso(@RequestBody GerenciadorBaseDTO gerenciador) {
        GerenciadorBase gerenciadorSalvo = gerenciadorService.primeiroAcesso(gerenciador);
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciadorSalvo);
    }

    @PostMapping("/novo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> criarGerenciador(@RequestBody GerenciadorBaseDTO novoGerenciador) {
        GerenciadorBase gerenciadorBaseSalvo = gerenciadorService.criarGerenciador(novoGerenciador);
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciadorBaseSalvo);
    }
}
