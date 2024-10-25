package br.com.javalirica.domain;

import br.com.javalirica.enums.Roles;

public class AdminGerenciador extends GerenciadorBase {

    public AdminGerenciador(String nome, String email, String senha) {
        super(nome, Roles.ADMIN ,email,senha);
    }

}