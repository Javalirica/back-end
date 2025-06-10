package br.com.javalirica.service;

import br.com.javalirica.domain.Leitor;
import br.com.javalirica.dto.leitor.LeitorCreateDTO;
import br.com.javalirica.dto.leitor.LeitorResponseDTO;
import br.com.javalirica.dto.leitor.LeitorUpdateDTO;
import br.com.javalirica.mapper.LeitorMapper;
import br.com.javalirica.repository.LeitorRepository;
import br.com.javalirica.service.exception.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LeitorService {

	private final LeitorRepository leitorRepository;
	private final LeitorMapper mapper;

	public LeitorService(LeitorRepository leitorRepository, LeitorMapper mapper) {
		this.leitorRepository = leitorRepository;
		this.mapper = mapper;
	}


	@Transactional
	public LeitorResponseDTO cadastrarLeitor(LeitorCreateDTO leitorCreateDTO) {

		validarLeitor(mapper.toEntity(leitorCreateDTO));
		Leitor entity = leitorRepository.save(mapper.toEntity(leitorCreateDTO));
		return mapper.toResponse(entity);
	}
	public LeitorResponseDTO buscarLeitorPorCpf(String cpf) {
		return mapper.toResponse(buscarEntidadePorCpf(cpf));
	}

	public List<LeitorResponseDTO> buscarTodosLeitores() {
		List<Leitor> leitores = leitorRepository.findAll();
		if (leitores.isEmpty()) {
			return Collections.emptyList();
		}
		return mapper.toListResponse(leitores);
	}

	@Transactional
	public LeitorResponseDTO atualizarLeitorPorCpf(String cpf, LeitorUpdateDTO leitorCreateDTO) {
		Leitor leitorBase = buscarEntidadePorCpf(cpf);

		atualizarDados(leitorBase, leitorCreateDTO);
		leitorRepository.save(leitorBase);
		return mapper.toResponse(leitorBase);
	}

	@Transactional
	public void desbloquearLeitor(String cpf) {
		Leitor leitorBase = buscarEntidadePorCpf(cpf);

		if (leitorBase.isBloqueado()) {
			leitorBase.setBloqueado(false);
		}
	}

	@Transactional
	public void bloquearLeitorPorCpf(String cpf) {
		Leitor leitor = buscarEntidadePorCpf(cpf);

		if (!leitor.isBloqueado()) {
			leitor.setBloqueado(true);
		}
	}

	private void atualizarDados(Leitor leitor, LeitorUpdateDTO objDados) {
		if (objDados.getNome() != null) {
			leitor.setNome(objDados.getNome());
		}
		if (objDados.getEmail() != null && !objDados.getEmail().equals(leitor.getEmail())) {
			validarEmailUnico(objDados.getEmail());
			leitor.setEmail(objDados.getEmail());
		}
		if (objDados.getCelular() != null && !objDados.getCelular().equals(leitor.getCelular())) {
			validarCelularUnico(objDados.getCelular());
			leitor.setCelular(objDados.getCelular());
		}
	}

	private void validarEmailUnico(String email) {
		if (leitorRepository.existsByEmail(email)) {
			throw new EmailJaExisteException("Já existe um usuário com este email");
		}
	}

	private void validarCelularUnico(String celular) {
		if (celular != null && leitorRepository.existsByCelular(celular)) {
			throw new LeitorInvalidoException("Já existe um usuário com este celular");
		}
	}

	private Leitor buscarEntidadePorCpf(String cpf) {
		if (cpf == null || cpf.isEmpty()) {
			throw new CpfInvalidException("CPF inválido ou nulo");
		}

		Leitor leitor = leitorRepository.findByCpf(cpf);
		if (leitor == null) {
			throw new LeitorNaoEncontradoException("Leitor não encontrado pelo CPF");
		}

		return leitor;
	}

	private void validarLeitor(Leitor leitor) {
		if (leitor == null ) {
			throw new LeitorInvalidoException("O leitor não pode ser nulo");
		}
		if (leitorRepository.existsByCpf(leitor.getCpf())) {
			throw new CpfInvalidException("CPF já cadastrado");
		}
		if (leitorRepository.existsByEmail(leitor.getEmail())) {
			throw new EmailJaExisteException("Já existe um usuário com este email");
		}
		if (leitor.getCelular() != null && leitorRepository.existsByCelular(leitor.getCelular())) {
			throw new LeitorInvalidoException("Já existe um usuário com este celular");
		}
	}
}
