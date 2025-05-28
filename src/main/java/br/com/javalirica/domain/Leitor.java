package br.com.javalirica.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @CPF(message = "CPF inválido")
    @NotNull
    @Column(unique = true, length = 11)
    private String cpf;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String celular;

    @OneToMany(mappedBy = "leitor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Emprestimo> livrosAlugados = new ArrayList<>();

    private boolean bloqueado;

    public Leitor(String nome, String cpf, String email, String celular) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.celular = celular;
        this.bloqueado = false;
    }
}
