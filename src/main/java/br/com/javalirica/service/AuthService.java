package br.com.javalirica.service;

import br.com.javalirica.dto.security.AcessDto;
import br.com.javalirica.dto.security.AuthenticationDTO;
import br.com.javalirica.repository.GerenciadorRepository;
import br.com.javalirica.security.jtw.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final GerenciadorRepository gerenciadorRepository;

	public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils, GerenciadorRepository gerenciadorRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.gerenciadorRepository = gerenciadorRepository;
	}

	public AcessDto login(AuthenticationDTO authDto) {
		String username = authDto.getEmail();
		try {
			System.out.println("Attempting to authenticate user: " + username);

			UsernamePasswordAuthenticationToken userAuth =
					new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword());

			Authentication authentication = authenticationManager.authenticate(userAuth);
			System.out.println("Authentication successful for user: " + username);

			GerenciadorDetails gerenciadorAuthenticate = (GerenciadorDetails) authentication.getPrincipal();
			String token = jwtUtils.generateTokenFromUserDetailsImpl(gerenciadorAuthenticate);
			System.out.println("Token generated for user: " + username);

			return new AcessDto(token);

		} catch (BadCredentialsException e) {
			System.out.println("Authentication failed for user: " + username);
			throw new BadCredentialsException("Email ou senha ivalidos");
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument provided during authentication: " + e.getMessage());
			e.printStackTrace();
		}
		return new AcessDto("Acesso negado");
	}
}