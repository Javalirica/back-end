package br.com.javalirica.service;

import br.com.javalirica.domain.AdminGerenciador;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.domain.SubAdminGerenciador;
import br.com.javalirica.enums.Roles;
import br.com.javalirica.repository.GerenciadorRepository;
import br.com.javalirica.service.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class GerenciadorService {

    private final GerenciadorRepository gerenciadorRepository;

    public GerenciadorService(GerenciadorRepository gerenciadorRepository) {
        this.gerenciadorRepository = gerenciadorRepository;
    }

    public GerenciadorBase criarGerenciador(String nome,Roles role, String email,String senha) {
        if (gerenciadorRepository.existsByEmail(email)){
             throw new UserAlreadyExistsException("Usuário já cadastrado");
        }
        // Encriptar a senha TODO
        switch (role) {
            case ADMIN:

                GerenciadorBase gerennciador = new AdminGerenciador(nome, email, senha);
                gerenciadorRepository.save(gerennciador);
                return gerennciador;
            case SUBADMIN:
                GerenciadorBase gerennciadorSub = new SubAdminGerenciador(nome, email,senha);
                gerenciadorRepository.save(gerennciadorSub);
                return  gerennciadorSub;
            default:
                throw new IllegalArgumentException("Role inválida");
        }
    }

}