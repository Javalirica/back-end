package br.com.javalirica.controller;

import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.livro.LivroDto;
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
    @GetMapping("/todos")
    public ResponseEntity<?> bucarTodos(){
        List<Livro> livros = livroService.listarTodos();
        return ResponseEntity.ok().body(livros);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/novo")
    public ResponseEntity<LivroDto> adicionarLivro(@RequestBody Livro livro) {
        LivroDto livroDto = livroService.adicionarLivro(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livroDto.getId()).toUri();
        return ResponseEntity.created(uri).body(livroDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{codigoLivro}")
    public ResponseEntity<Void> removerLivro(@PathVariable String codigoLivro) {
        livroService.removerLivro(codigoLivro);
        return ResponseEntity.noContent().build();
    }
}