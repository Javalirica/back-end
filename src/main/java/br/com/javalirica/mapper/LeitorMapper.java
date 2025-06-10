package br.com.javalirica.mapper;

import br.com.javalirica.domain.Leitor;
import br.com.javalirica.dto.leitor.LeitorCreateDTO;
import br.com.javalirica.dto.leitor.LeitorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LeitorMapper {

	Leitor toEntity (LeitorCreateDTO leitor);

	LeitorResponseDTO toResponse(Leitor leitor);

	List<LeitorResponseDTO> toListResponse (List<Leitor> leitores);
}
