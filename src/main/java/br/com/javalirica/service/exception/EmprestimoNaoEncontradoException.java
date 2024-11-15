package br.com.javalirica.service.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {

    public EmprestimoNaoEncontradoException(String error){
        super(error);
    }
}
