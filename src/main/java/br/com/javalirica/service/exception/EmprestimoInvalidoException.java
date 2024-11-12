package br.com.javalirica.service.exception;

public class EmprestimoInvalidoException  extends RuntimeException {

    public EmprestimoInvalidoException (String error){
        super(error);
    }
}
