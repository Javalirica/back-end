package br.com.javalirica.domain;

import br.com.javalirica.enums.Roles;
import jakarta.persistence.Entity;

@Entity
public class SubAdminGerenciador extends GerenciadorBase {

    public SubAdminGerenciador(String nome, String email,String senha) {
        super(nome, Roles.SUBADMIN, email, senha );
    }


}
