package br.com.javalirica.service.exception;

public class EmailJaExisteException extends RuntimeException{

    public EmailJaExisteException(String msg){
        super(msg);
    }
}
