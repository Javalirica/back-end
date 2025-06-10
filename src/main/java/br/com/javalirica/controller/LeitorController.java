package br.com.javalirica.controller;

import br.com.javalirica.dto.leitor.LeitorCreateDTO;
import br.com.javalirica.dto.leitor.LeitorResponseDTO;
import br.com.javalirica.dto.leitor.LeitorUpdateDTO;
import br.com.javalirica.service.LeitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<LeitorResponseDTO> novoLeitor(@Valid @RequestBody LeitorCreateDTO leitor){
		LeitorResponseDTO leitorResponse = leitorService.cadastrarLeitor(leitor);
		return ResponseEntity.status(HttpStatus.CREATED).body(leitorResponse)	;
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<LeitorResponseDTO> buscarLeitor(@PathVariable String cpf){
		LeitorResponseDTO leitor = leitorService.buscarLeitorPorCpf(cpf);
		return ResponseEntity.ok().body(leitor);
	}

	@GetMapping("/todos")
	public ResponseEntity<List<LeitorResponseDTO>> buscarTodos(){
		List<LeitorResponseDTO> leitores = leitorService.buscarTodosLeitores();
		return ResponseEntity.ok().body(leitores);
	}

	@PutMapping("/{cpf}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LeitorResponseDTO> atualizarLeitor(@PathVariable String cpf,@Valid @RequestBody LeitorUpdateDTO leitor){
		LeitorResponseDTO leitorUpdate = leitorService.atualizarLeitorPorCpf(cpf,leitor);
		return ResponseEntity.ok().body(leitorUpdate);
	}

	@PutMapping("/bloquear/{cpf}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> bloquearLeitor(@PathVariable String cpf){
		leitorService.bloquearLeitorPorCpf(cpf);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/desbloquear/{cpf}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> desbloquearLeitor(@PathVariable String cpf){
		leitorService.desbloquearLeitor(cpf);
		return ResponseEntity.noContent().build();
	}
}
