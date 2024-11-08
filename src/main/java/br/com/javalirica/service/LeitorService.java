package br.com.javalirica.service;

import br.com.javalirica.domain.Leitor;
import br.com.javalirica.repository.LeitorRepository;
import org.springframework.stereotype.Service;

@Service
public class LeitorService {

	private final LeitorRepository leitorRepository;


	public LeitorService(LeitorRepository leitorRepository) {
		this.leitorRepository = leitorRepository;
	}

	public void novoLeitor(Leitor leitor){}

	public void buscarLeitorPorCpf(String cpf){}

	public void buscarTodosLeitores(String cpf){}

	public void atualizarLeitorPorCpf(String cpf){}

	public void deletarLeitorPorCpf(String cpf){}

	private void updateDados(Leitor leitor){}

}
