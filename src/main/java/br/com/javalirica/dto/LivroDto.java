package br.com.javalirica.dto;

import br.com.javalirica.domain.Livro;

public class LivroDto {

    private String autor;
    private String nome;
    private Boolean disponivel;

    public LivroDto(Livro livro) {
        this.autor = livro.getAutor();
        this.nome = livro.getNome();
        this.disponivel = livro.isDisponivel();
    }
}
