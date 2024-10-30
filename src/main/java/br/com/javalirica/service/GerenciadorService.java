package br.com.javalirica.service;

import br.com.javalirica.domain.AdminGerenciador;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.domain.SubAdminGerenciador;
import br.com.javalirica.dto.GerenciadorBaseDTO;
import br.com.javalirica.enums.Roles;
import br.com.javalirica.repository.GerenciadorRepository;
import br.com.javalirica.service.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorService {

    private final GerenciadorRepository gerenciadorRepository;
    private final PasswordEncoder passwordEncoder;

    public GerenciadorService(GerenciadorRepository gerenciadorRepository, PasswordEncoder passwordEncoder) {
        this.gerenciadorRepository = gerenciadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public GerenciadorBase criarGerenciador(GerenciadorBaseDTO gerenciadorBase) {
        if (gerenciadorRepository.existsByEmail(gerenciadorBase.getEmail())){
             throw new UserAlreadyExistsException("Usuário já cadastrado");
        }
        switch (gerenciadorBase.getRole()) {
            case ADMIN:

                GerenciadorBase gerennciador = new AdminGerenciador(gerenciadorBase.getNome(),
                        gerenciadorBase.getEmail(), passwordEncoder.encode(gerenciadorBase.getSenha()));
                gerenciadorRepository.save(gerennciador);
                return gerennciador;
            case SUBADMIN:
                GerenciadorBase gerennciadorSub = new SubAdminGerenciador(gerenciadorBase.getNome(),
                        gerenciadorBase.getEmail(), passwordEncoder.encode(gerenciadorBase.getSenha()));
                gerenciadorRepository.save(gerennciadorSub);
                return  gerennciadorSub;
            default:
                throw new IllegalArgumentException("Role inválida");
        }
    }

}