package br.com.javalirica.service.exception;

public class LivroInvalidoException extends RuntimeException{

    public LivroInvalidoException(String msg){
        super(msg);
    }
}
