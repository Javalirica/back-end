package br.com.javalirica.dto;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class GerenciadorBaseDTO {

    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "O papel (role) não pode ser nulo")
    private Roles role;

    @Email(message = "Email deve ser válido")
    @NotNull(message = "O email não pode ser nulo")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    public GerenciadorBaseDTO() {
    }

    public GerenciadorBaseDTO(GerenciadorBase gerenciador) {
        this.nome = gerenciador.getNome();
        this.role = gerenciador.getRole();
        this.email = gerenciador.getEmail();
        this.senha = gerenciador.getSenha();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
