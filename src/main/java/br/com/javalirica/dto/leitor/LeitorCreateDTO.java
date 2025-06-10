package br.com.javalirica.dto.leitor;

import br.com.javalirica.domain.Emprestimo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeitorCreateDTO {

    private Long id;

    @NotNull
    private String nome;

    @CPF(message = "CPF inválido")
    @NotNull
    private  String cpf;

    @Email
    @NotNull(message = "e-mail invalido")
    private String email;

    @NotNull
    @Size(min = 11, max = 11)
    private int celular;

    private List<Emprestimo> livrosAlugados = new ArrayList<>();

    private boolean bloqueado;

}
