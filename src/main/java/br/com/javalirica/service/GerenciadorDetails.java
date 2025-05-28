package br.com.javalirica.service;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.enums.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class GerenciadorDetails implements UserDetails {

	private String nome;
	private String email;
	private String password;
	private Roles role;
	private Collection<? extends GrantedAuthority> authorities;

	public GerenciadorDetails(String nome, String password, String email, Roles role,
							  Collection<? extends GrantedAuthority> authorities) {
		this.nome = nome;
		this.password = password;
		this.email = email;
		this.role = role;
		this.authorities = authorities;
	}

	public static GerenciadorDetails build(GerenciadorBase gerenciador) {
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + gerenciador.getRole().name()));
		return new GerenciadorDetails(
				gerenciador.getNome(),
				gerenciador.getSenha(),
				gerenciador.getEmail(),
				gerenciador.getRole(),
				authorities
		);
	}

	public Roles getRole() {
		return this.role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
