package br.com.javalirica.service.exception;

public class GerenciadorJaExistenteException extends RuntimeException{

    public GerenciadorJaExistenteException(String msg){
        super(msg);
    }
}
