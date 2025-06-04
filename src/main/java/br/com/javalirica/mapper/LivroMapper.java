package br.com.javalirica.mapper;

import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.livro.LivroRequestDTO;
import br.com.javalirica.dto.livro.LivroResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LivroMapper {

	LivroResponseDTO toRespose(Livro livro);

	Livro toEntity(LivroRequestDTO request);

	List<LivroResponseDTO> toListResponse (List<Livro> livros);
}
