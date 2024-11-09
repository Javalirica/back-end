package br.com.javalirica.dto;

import br.com.javalirica.domain.Emprestimo;
import br.com.javalirica.domain.Leitor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

public class LeitorDto {

    private Long id;

    private String nome;

    private  String cpf;

    private String email;

    private String celular;

    private List<Emprestimo> livrosAlugados = new ArrayList<>();

    private boolean bloqueado;

    public LeitorDto() {
    }

    public LeitorDto(Leitor leitor) {
        this.id = leitor.getId();
        this.nome = leitor.getNome();
        this.cpf = leitor.getCpf();
        this.email = leitor.getEmail();
        this.celular = leitor.getCelular();
        this.livrosAlugados = leitor.getLivrosAlugados();
        this.bloqueado = leitor.isBloqueado();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
