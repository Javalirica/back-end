package br.com.javalirica.dto.emprestimo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmprestimoRequestDTO {

    private String cpf;

    private String codigoLivro;

    public EmprestimoRequestDTO(String cpf, String codigoLivro) {
        this.cpf = cpf;
        this.codigoLivro = codigoLivro;
    }

}
