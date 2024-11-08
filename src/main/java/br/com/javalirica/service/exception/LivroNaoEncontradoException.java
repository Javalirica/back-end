package br.com.javalirica.service.exception;

public class LivroNaoEncontradoException extends RuntimeException{

    public LivroNaoEncontradoException(String msg){
        super(msg);
    }
}
