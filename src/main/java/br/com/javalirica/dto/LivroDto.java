package br.com.javalirica.dto;

import br.com.javalirica.domain.Livro;


public class LivroDto {

    private Long id;
    private String autor;
    private String nome;
    private Boolean disponivel;

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.autor = livro.getAutor();
        this.nome = livro.getNome();
        this.disponivel = livro.isDisponivel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
