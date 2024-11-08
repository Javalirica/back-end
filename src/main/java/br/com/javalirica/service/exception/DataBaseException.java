package br.com.javalirica.service.exception;

public class DataBaseException extends RuntimeException{

    public DataBaseException(String msg){
        super(msg);
    }

    public DataBaseException(String mensagem, Throwable causa) {
        super(mensagem, causa);  // Passa a mensagem e a causa (exceção original) para o construtor da classe pai
    }
}
