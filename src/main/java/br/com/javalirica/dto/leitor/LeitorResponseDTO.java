package br.com.javalirica.dto.leitor;

public record LeitorResponseDTO(long id,String nome, String cpf, String email, String celular,  boolean bloqueado ) {
}
