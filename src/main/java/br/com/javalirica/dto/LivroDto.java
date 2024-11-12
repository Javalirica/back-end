package br.com.javalirica.dto;

import br.com.javalirica.domain.Livro;

import java.util.Random;


public class LivroDto {

    private Long id;
    private String autor;
    private String nome;
    private String codigoLivro;
    private String categoria;
    private Boolean disponivel;

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.autor = livro.getAutor();
        this.nome = livro.getNome();
        this.codigoLivro=livro.getCodigoLivro();
        this.categoria= livro.getCategoria();
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

    public String getCodigoLivro() {
        return codigoLivro;
    }

    public String getCategoria() {
        return categoria;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
