package br.com.javalirica.service.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException (String msg){
        super(msg);
    }
}
