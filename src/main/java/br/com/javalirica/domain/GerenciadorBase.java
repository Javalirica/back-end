package br.com.javalirica.domain;

import br.com.javalirica.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of ="id")
@Entity
public abstract class GerenciadorBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID id;

    private  String nome;

    private  Roles role;

    private  String email;

    private  String senha;

    public GerenciadorBase(String nome, Roles role, String email, String senha) {
        this.nome = nome;
        this.role = role;
        this.email = email;
        this.senha = senha;
    }

}
