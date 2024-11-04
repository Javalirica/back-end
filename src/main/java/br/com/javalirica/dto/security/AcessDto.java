package br.com.javalirica.dto.security;

public class AcessDto {

	private String token;

	//TODO implementar retornar o usuario e liberacoes (authorities)

	public AcessDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
