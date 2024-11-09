package br.com.javalirica.service.exception;

public class LeitorNaoEncontradoException extends RuntimeException{

    public LeitorNaoEncontradoException(String msg){
        super(msg);
    }
}
