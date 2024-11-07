package br.com.javalirica.service;

import br.com.javalirica.domain.AdminGerenciador;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.domain.SubAdminGerenciador;
import br.com.javalirica.dto.GerenciadorBaseDTO;
import br.com.javalirica.enums.Roles;
import br.com.javalirica.repository.GerenciadorRepository;
import br.com.javalirica.service.exception.GerenciadorJaExistenteException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenciadorService {

    private final GerenciadorRepository gerenciadorRepository;
    private final PasswordEncoder passwordEncoder;

    public GerenciadorService(GerenciadorRepository gerenciadorRepository, PasswordEncoder passwordEncoder) {
        this.gerenciadorRepository = gerenciadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public GerenciadorBase primeiroAcesso(GerenciadorBaseDTO admDto){
        List<GerenciadorBase>gerenciadoresAdmin = gerenciadorRepository.findAllByRole(Roles.ADMIN);
        System.out.println(gerenciadoresAdmin);
        System.out.println("a senha cadastrada do dto" + admDto.getSenha());
        if (gerenciadoresAdmin.isEmpty()) {
            GerenciadorBase adm =  new AdminGerenciador(admDto.getNome(),
                    admDto.getEmail(), passwordEncoder.encode(admDto.getSenha()));
            return  gerenciadorRepository.save(adm);
        } else {
            throw new GerenciadorJaExistenteException("Já existe um Admin cadastrado no sistema");
        }

    }

    public GerenciadorBase criarGerenciador(GerenciadorBaseDTO gerenciadorBase) {

        if (gerenciadorRepository.existsByEmail(gerenciadorBase.getEmail())){
             throw new GerenciadorJaExistenteException("Usuário já cadastrado");
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