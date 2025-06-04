package br.com.javalirica.dto.livro;

import br.com.javalirica.domain.Livro;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LivroRequestDTO {

	@NotNull
	private String nome;

	@NotNull
	private String autor;

	@NotNull
	@Size(max = 5)
	private String codigoLivro;

	private String categoria;

	public LivroRequestDTO(Livro livro) {

		this.autor = livro.getAutor();
		this.nome = livro.getNome();
		this.codigoLivro=livro.getCodigoLivro();
		this.categoria= livro.getCategoria();
	}
}
