package br.com.javalirica.controller;

import br.com.javalirica.domain.Leitor;
import br.com.javalirica.service.LeitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/leitor")
public class LeitorController {

	private final LeitorService leitorService;

	public LeitorController(LeitorService leitorService) {
		this.leitorService = leitorService;
	}

	@PostMapping
	public ResponseEntity<?> novoLeitor(@RequestBody Leitor leitor){}

	@GetMapping
	public ResponseEntity<?> buscarLeitor(@PathVariable String cpf){}

	@GetMapping
	public ResponseEntity<?> buscarTodos(@PathVariable String cpf){}

	@PutMapping
	public ResponseEntity<?> atualizarLeitor(@RequestBody Leitor leitor){}

	@DeleteMapping
	public ResponseEntity<?> deletarLeitor(@PathVariable String cpf){}
}
