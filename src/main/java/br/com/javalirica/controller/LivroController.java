package br.com.javalirica.controller;

import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.LivroDto;
import br.com.javalirica.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/v1/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok().body(livroService.listarTodos());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<LivroDto>> findByName (@RequestParam String nome){
        List <Livro> livros = livroService.buscarPorNome(nome);
        List <LivroDto> livrosDto = livros.stream().map(livro -> new LivroDto(livro)).collect(Collectors.toList());
        return ResponseEntity.ok().body(livrosDto);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
//        livroService.adicionarLivro(livro);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livro.getId()).toUri();
//        return ResponseEntity.created(uri).body(livro);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }
}

