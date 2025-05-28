package br.com.javalirica.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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


    public Emprestimo(Livro livro, Leitor leitor, LocalDate dataDeEmprestimo, LocalDate dataLimiteEntrega) {
        this.livro = livro;
        this.leitor = leitor;
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.dataLimiteEntrega = dataLimiteEntrega;
    }

}
