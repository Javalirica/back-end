package br.com.javalirica.mapper;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.dto.gerenciador.GerenciadorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface GerenciadorMapStructMapper {


	List<GerenciadorResponseDTO>  toResponseDTOList(List<GerenciadorBase> lista);

	GerenciadorResponseDTO toResponseDTO(GerenciadorBase gerenciador);
}
