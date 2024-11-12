package br.com.javalirica.dto;

public class EmprestimoDto {

    private String cpf;

    private String codigoLivro;

    public EmprestimoDto() {
    }

    public EmprestimoDto(String cpf, String codigoLivro) {
        this.cpf = cpf;
        this.codigoLivro = codigoLivro;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCodigoLivro() {
        return codigoLivro;
    }

}
