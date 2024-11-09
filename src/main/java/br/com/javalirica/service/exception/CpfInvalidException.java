package br.com.javalirica.service.exception;

public class CpfInvalidException extends RuntimeException{

    public CpfInvalidException(String msg){
        super(msg);
    }
}
