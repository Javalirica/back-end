package br.com.javalirica.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public Livro(String nome, String codigoLivro, String autor, String categoria) {
        this.nome = nome;
        this.codigoLivro = codigoLivro;
        this.autor = autor;
        this.categoria = categoria;
    }

}
