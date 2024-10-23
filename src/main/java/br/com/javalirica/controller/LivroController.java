package br.com.javalirica.controller;

import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.LivroDto;
import br.com.javalirica.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/name")
    public ResponseEntity<LivroDto> findByName (@PathVariable String nome){
        Livro livro = livroService.buscarPorNome(nome);
        LivroDto dto = new LivroDto(livro);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        livroService.adicionarLivro(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable String nome) {
        livroService.removerLivro(nome);
        return ResponseEntity.noContent().build();
    }

}


