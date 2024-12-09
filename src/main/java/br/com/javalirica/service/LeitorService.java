package br.com.javalirica.service;

import br.com.javalirica.domain.Leitor;
import br.com.javalirica.dto.LeitorDto;
import br.com.javalirica.repository.LeitorRepository;
import br.com.javalirica.service.exception.CpfInvalidException;
import br.com.javalirica.service.exception.DataBaseException;
import br.com.javalirica.service.exception.EmailJaExisteException;
import br.com.javalirica.service.exception.LeitorNaoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeitorService {

	private final LeitorRepository leitorRepository;

	public LeitorService(LeitorRepository leitorRepository) {
		this.leitorRepository = leitorRepository;
	}
	@Transactional
	public LeitorDto cadastrarLeitor(Leitor leitor) {
		if (leitor == null) {
			throw new IllegalArgumentException("O leitor não pode ser nulo");
		}
		try {
			leitorRepository.save(leitor);
			return converterParaDto(leitor);
		} catch (Exception e) {
			throw new DataBaseException("Erro ao salvar um novo leitor", e);
		}
	}
	public LeitorDto buscarLeitorPorCpf(String cpf) {
		if (cpf == null || cpf.isEmpty()) {
			throw new CpfInvalidException("CPF inválido ou nulo");
		}
		Leitor leitor = leitorRepository.findByCpf(cpf);
		if (leitor != null) {
			return converterParaDto(leitor);
		} else {
			throw new LeitorNaoEncontradoException("Leitor não encontrado pelo CPF");
		}
	}

	public List<LeitorDto> buscarTodosLeitores() {
		List<Leitor> leitores = leitorRepository.findAll();
		if (leitores.isEmpty()) {
			return Collections.emptyList();
		}
		return leitores.stream().map(this::converterParaDto).collect(Collectors.toList());
	}

	@Transactional
	public LeitorDto atualizarLeitorPorCpf(String cpf, LeitorDto leitorDto) {
		Leitor leitorBase = converterDtoParaLeitor(buscarLeitorPorCpf(cpf));

		atualizarDados(leitorBase, leitorDto);
		leitorRepository.save(leitorBase);
		return converterParaDto(leitorBase);
	}

	@Transactional
	public void desbloquearLeitor(String cpf) {
		Leitor leitorBase = converterDtoParaLeitor(buscarLeitorPorCpf(cpf));
		leitorBase.setBloqueado(false);
	}

	@Transactional
	public void bloquearLeitorPorCpf(String cpf) {
		Leitor leitor = converterDtoParaLeitor(buscarLeitorPorCpf(cpf));

		if (!leitor.isBloqueado()) {
			leitor.setBloqueado(true);
		}
	}

	private LeitorDto converterParaDto(Leitor leitor) {
		return new LeitorDto(leitor);
	}
	private Leitor converterDtoParaLeitor(LeitorDto leitor) {
        return leitorRepository.findByCpf(leitor.getCpf());
	}

	private void atualizarDados(Leitor leitor, LeitorDto objDados) {
		if (objDados.getNome() != null){
			leitor.setNome(objDados.getNome());
		}
		if (objDados.getEmail() != null && (!objDados.getEmail().equals(leitor.getEmail())) && !leitorRepository.existsByEmail(objDados.getEmail())) {
			leitor.setEmail(objDados.getEmail());
		} else if (objDados.getEmail() != null) {
			throw new EmailJaExisteException("Já existe um usuário com este email");
		}
		if (objDados.getCelular()!= null){
			leitor.setCelular(objDados.getCelular());
		}
	}
}
