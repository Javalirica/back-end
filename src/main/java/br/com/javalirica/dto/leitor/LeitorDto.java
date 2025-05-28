package br.com.javalirica.dto.leitor;

import br.com.javalirica.domain.Emprestimo;
import br.com.javalirica.domain.Leitor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LeitorDto {

    private Long id;

    @NotNull
    private String nome;

    @CPF(message = "CPF inválido")
    @NotNull
    private  String cpf;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String celular;

    private List<Emprestimo> livrosAlugados = new ArrayList<>();

    private boolean bloqueado;


    public LeitorDto(Leitor leitor) {

        this.nome = leitor.getNome();
        this.cpf = leitor.getCpf();
        this.email = leitor.getEmail();
        this.celular = leitor.getCelular();
        this.livrosAlugados = leitor.getLivrosAlugados();
        this.bloqueado = leitor.isBloqueado();
    }

}
