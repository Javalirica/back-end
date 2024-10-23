package br.com.javalirica.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import javax.management.relation.Role;
import java.util.UUID;

public class Gerenciador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private Role role;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String senha;
}
