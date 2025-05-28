package br.com.javalirica.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmprestimoDto {

    private String cpf;

    private String codigoLivro;

    public EmprestimoDto(String cpf, String codigoLivro) {
        this.cpf = cpf;
        this.codigoLivro = codigoLivro;
    }

}
