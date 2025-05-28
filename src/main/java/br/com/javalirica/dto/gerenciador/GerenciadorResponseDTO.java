package br.com.javalirica.dto.gerenciador;

import br.com.javalirica.enums.Roles;

public record GerenciadorResponseDTO (String nome, Roles role, String email){}
