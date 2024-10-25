package br.com.javalirica.enums;

public enum Roles {
    ADMIN(1, "admin"),
    SUBADMIN(2, "subadmin");

    private final int codigo;
    private final String nome;

    Roles(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
