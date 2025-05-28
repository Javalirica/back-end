package br.com.javalirica.dto;

import br.com.javalirica.domain.Livro;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LivroDto {

    private Long id;
    private String autor;
    private String nome;
    private String codigoLivro;
    private String categoria;
    private Boolean disponivel;

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.autor = livro.getAutor();
        this.nome = livro.getNome();
        this.codigoLivro=livro.getCodigoLivro();
        this.categoria= livro.getCategoria();
        this.disponivel = livro.isDisponivel();
    }

}
