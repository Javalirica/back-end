package br.com.javalirica.controller;

import br.com.javalirica.dto.livro.LivroRequestDTO;
import br.com.javalirica.dto.livro.LivroResponseDTO;
import br.com.javalirica.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }


    @GetMapping("/nome")
    public ResponseEntity<List<LivroResponseDTO>> findByName (@RequestParam String nome){
        List <LivroResponseDTO> livrosDto = livroService.buscarPorNome(nome);
        return ResponseEntity.ok().body(livrosDto);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        return ResponseEntity.ok().body(livroService.listarTodos());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/novo")
    public ResponseEntity<LivroResponseDTO> adicionarLivro(@RequestBody LivroRequestDTO livro) {
        LivroResponseDTO livroResponseDTO = livroService.adicionarLivro(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livroResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(livroResponseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{codigoLivro}")
    public ResponseEntity<Void> removerLivro(@PathVariable String codigoLivro) {
        livroService.removerLivro(codigoLivro);
        return ResponseEntity.noContent().build();
    }
}