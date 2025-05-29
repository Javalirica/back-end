package br.com.javalirica.controller;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.dto.gerenciador.GerenciadorBaseRequestDTO;
import br.com.javalirica.dto.gerenciador.GerenciadorResponseDTO;
import br.com.javalirica.security.jtw.JwtUtils;
import br.com.javalirica.service.GerenciadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/gerenciador")
public class GerenciadorController {

    private final GerenciadorService gerenciadorService;

    public GerenciadorController(GerenciadorService gerenciadorService, JwtUtils jwtUtils) {
        this.gerenciadorService = gerenciadorService;
    }

    @PostMapping("/primeiro")
    public ResponseEntity<GerenciadorResponseDTO>primeiroAcesso(@RequestBody GerenciadorBaseRequestDTO gerenciador) {
        GerenciadorResponseDTO gerenciadorSalvo = gerenciadorService.primeiroAcesso(gerenciador);
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciadorSalvo);
    }

    @PostMapping("/novo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GerenciadorResponseDTO> criarGerenciador(@RequestBody GerenciadorBaseRequestDTO novoGerenciador) {
        GerenciadorResponseDTO gerenciadorBaseSalvo = gerenciadorService.criarGerenciador(novoGerenciador);
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenciadorBaseSalvo);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<GerenciadorResponseDTO>> buscarTodos() {
        List<GerenciadorResponseDTO> gerenciadores = gerenciadorService.buscarTodos();
        return ResponseEntity.ok().body(gerenciadores);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<GerenciadorResponseDTO>> buscarPorNome(@PathVariable String nome) {
        List<GerenciadorResponseDTO> gerenciadorDTO = gerenciadorService.buscarPorNome(nome);
        return ResponseEntity.ok().body(gerenciadorDTO);
    }
}