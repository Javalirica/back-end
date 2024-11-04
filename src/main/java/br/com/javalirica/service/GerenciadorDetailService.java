package br.com.javalirica.service;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.repository.GerenciadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorDetailService implements UserDetailsService {

	@Autowired
	private GerenciadorRepository gerenciadorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		GerenciadorBase gerenciador = gerenciadorRepository.findByEmail(email);
		if (gerenciador == null) {

			throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
		}
		return GerenciadorDetails.build(gerenciador);
	}
}
