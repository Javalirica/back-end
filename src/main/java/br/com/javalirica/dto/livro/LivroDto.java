package br.com.javalirica.dto.livro;

import br.com.javalirica.domain.Livro;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LivroDto {

    private Long id;

    @NotNull
    private String autor;

    @NotNull
    private String nome;

    @NotNull
    private String codigoLivro;
    private String categoria;

    public LivroDto(Livro livro) {

        this.autor = livro.getAutor();
        this.nome = livro.getNome();
        this.codigoLivro=livro.getCodigoLivro();
        this.categoria= livro.getCategoria();
    }

}
