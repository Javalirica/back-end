package br.com.javalirica.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "leitor_id", nullable = false)
    private Leitor leitor;

    private LocalDate dataDeEmprestimo;
    private LocalDate dataLimiteEntrega;
    private LocalDate dataDeEntrega;
    private boolean devolvido = false;

    public Emprestimo() {
    }

    public Emprestimo(Livro livro, Leitor leitor, LocalDate dataDeEmprestimo, LocalDate dataLimiteEntrega) {
        this.livro = livro;
        this.leitor = leitor;
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.dataLimiteEntrega = dataLimiteEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public LocalDate getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public void setDataDeEmprestimo(LocalDate dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
    }

    public LocalDate getDataLimiteEntrega() {
        return dataLimiteEntrega;
    }

    public void setDataLimiteEntrega(LocalDate dataLimiteEntrega) {
        this.dataLimiteEntrega = dataLimiteEntrega;
    }

    public LocalDate getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(LocalDate dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
