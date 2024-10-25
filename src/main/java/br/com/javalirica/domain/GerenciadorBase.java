package br.com.javalirica.domain;

import br.com.javalirica.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;
@Entity
public abstract class GerenciadorBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected  UUID id;

    @NotNull
    protected  String nome;

    @NotNull
    protected  Roles role;

    @Email
    @NotNull
    protected  String email;

    @NotNull
    protected  String senha;

    public GerenciadorBase(String nome, Roles role, String email, String senha) {
        this.nome = nome;
        this.role = role;
        this.email = email;
        this.senha = senha;
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
