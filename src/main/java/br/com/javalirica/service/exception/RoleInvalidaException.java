package br.com.javalirica.service.exception;

public class RoleInvalidaException extends RuntimeException{

    public RoleInvalidaException(String msg){
        super(msg);
    }
}
