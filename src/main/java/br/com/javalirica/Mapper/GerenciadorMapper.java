package br.com.javalirica.Mapper;

import br.com.javalirica.domain.AdminGerenciador;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.domain.SubAdminGerenciador;
import br.com.javalirica.dto.gerenciador.GerenciadorBaseRequestDTO;
import br.com.javalirica.dto.gerenciador.GerenciadorResponseDTO;
import br.com.javalirica.service.exception.RoleInvalidaException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GerenciadorMapper {

	private final GerenciadorMapStructMapper mapStructMapper;
	private final PasswordEncoder passwordEncoder;

	public GerenciadorMapper(GerenciadorMapStructMapper mapStructMapper, PasswordEncoder passwordEncoder) {
		this.mapStructMapper = mapStructMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public GerenciadorBase toGerenciadorEntity(GerenciadorBaseRequestDTO dto){

		switch (dto.getRole()) {
			case ADMIN:
				GerenciadorBase gerenciadorAdmin = new AdminGerenciador(dto.getNome(),
						dto.getEmail(), passwordEncoder.encode(dto.getSenha()));
				return gerenciadorAdmin;

			case SUBADMIN:
				GerenciadorBase gerenciadorSub = new SubAdminGerenciador(dto.getNome(),
						dto.getEmail(), passwordEncoder.encode(dto.getSenha()));
				return gerenciadorSub;

			default:
				throw new RoleInvalidaException("Role inválida");
		}
	}

	public List<GerenciadorResponseDTO> toResponseDTOList(List<GerenciadorBase> lista){
		return  mapStructMapper.toResponseDTOList(lista);
	}

	public GerenciadorResponseDTO toResponseDTO(GerenciadorBase entity) {
		return mapStructMapper.toResponseDTO(entity);
	}

}
