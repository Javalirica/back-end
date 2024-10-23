package br.com.javalirica.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String celular;

    @OneToMany(mappedBy = "leitor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Emprestimo> livrosAlugados = new ArrayList<>();

    public Leitor() {
    }

    public Leitor(String nome, String email, String celular) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<Emprestimo> getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setLivrosAlugados(List<Emprestimo> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

}
