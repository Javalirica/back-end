package br.com.javalirica.service.exception;

public class GerenciadorNaoEncontradoException extends RuntimeException {


	public GerenciadorNaoEncontradoException(String e) {
		super(e);
	}

	public GerenciadorNaoEncontradoException() {
		super("Gerenciador não encontrado ou Nulo");
	}
}
