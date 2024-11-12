package br.com.javalirica.controller;

import br.com.javalirica.domain.Leitor;
import br.com.javalirica.dto.LeitorDto;
import br.com.javalirica.service.LeitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/leitor")
public class LeitorController {

	private final LeitorService leitorService;

	public LeitorController(LeitorService leitorService) {
		this.leitorService = leitorService;
	}

	@PostMapping("/novo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> novoLeitor(@RequestBody Leitor leitor){
		LeitorDto leitorDto = leitorService.cadastrarLeitor(leitor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(leitorDto.getId()).toUri();
		return ResponseEntity.created(uri).body(leitorDto);
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<?> buscarLeitor(@PathVariable String cpf){
		LeitorDto leitor = leitorService.buscarLeitorPorCpf(cpf);
		return ResponseEntity.ok().body(leitor);
	}

	@GetMapping("/todos")
	public ResponseEntity<?> buscarTodos(){
		List<LeitorDto> leitores = leitorService.buscarTodosLeitores();
		return ResponseEntity.ok().body(leitores);
	}

	@PutMapping("/{cpf}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> atualizarLeitor(@PathVariable String cpf,@RequestBody LeitorDto leitor){
		LeitorDto leitorUpdate = leitorService.atualizarLeitorPorCpf(cpf,leitor);
		return ResponseEntity.ok().body(leitorUpdate);
	}

	@DeleteMapping("{cpf}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deletarLeitor(@PathVariable String cpf){
		leitorService.deletarLeitorPorCpf(cpf);
		return ResponseEntity.noContent().build();
	}
}
