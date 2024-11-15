package br.com.javalirica.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Random;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;

    @Column(unique = true, length = 5)
    @NotNull
    private String codigoLivro;

    @NotNull
    private String autor;

    private String categoria;

    private boolean disponivel = true;


    public Livro() {
    }

    public Livro(String nome, String codigoLivro, String autor, String categoria) {
        this.nome = nome;
        this.codigoLivro = codigoLivro;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
