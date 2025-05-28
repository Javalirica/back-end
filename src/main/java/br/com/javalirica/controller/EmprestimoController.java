package br.com.javalirica.controller;

import br.com.javalirica.domain.Emprestimo;
import br.com.javalirica.dto.emprestimo.EmprestimoRequestDTO;
import br.com.javalirica.service.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Emprestimo>> listarTodos(){
        List<Emprestimo> emprestimos = emprestimoService.consultarEmprestimosNaoDevolvidos();
        return ResponseEntity.ok().body(emprestimos);
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<List<Emprestimo>> buscarEmprestimo(@PathVariable String cpf) {
        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimo(cpf);
        return ResponseEntity.ok().body(emprestimos);
    }


    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Emprestimo> emprestarLivro(@RequestBody EmprestimoRequestDTO emprestimo) {
        Emprestimo emprestimoObj = emprestimoService.emprestarLivro(emprestimo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(emprestimoObj.getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimoObj);
    }

    @PostMapping("/finalizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> finalizarEmprestimo(@PathVariable Long id){
        emprestimoService.registrarDevolucao(id);
        return ResponseEntity.noContent().build();

    }
}
