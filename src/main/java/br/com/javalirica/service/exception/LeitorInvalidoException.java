package br.com.javalirica.service.exception;

public class LeitorInvalidoException extends RuntimeException{

	public  LeitorInvalidoException(String error){
		 super(error);
	}
}
