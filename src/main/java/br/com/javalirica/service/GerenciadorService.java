package br.com.javalirica.service;

import br.com.javalirica.domain.AdminGerenciador;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.domain.SubAdminGerenciador;
import br.com.javalirica.dto.GerenciadorBaseDTO;
import br.com.javalirica.enums.Roles;
import br.com.javalirica.repository.GerenciadorRepository;
import br.com.javalirica.service.exception.DataBaseException;
import br.com.javalirica.service.exception.GerenciadorJaExistenteException;
import br.com.javalirica.service.exception.RoleInvalidaException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class GerenciadorService {

    private final GerenciadorRepository gerenciadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public GerenciadorService(GerenciadorRepository gerenciadorRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.gerenciadorRepository = gerenciadorRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public List<GerenciadorBase> buscarTodos(){
        return gerenciadorRepository.findAll();
    }
    @Transactional
    public GerenciadorBase primeiroAcesso(GerenciadorBaseDTO admDto) throws DataBaseException {
        List<GerenciadorBase>gerenciadoresAdmin = gerenciadorRepository.findAllByRole(Roles.ADMIN);

        if (!gerenciadoresAdmin.isEmpty()) {
            throw new GerenciadorJaExistenteException("Já existe um administrador cadastrado no sistema.");
        }

        try {
            GerenciadorBase adm = new AdminGerenciador(admDto.getNome(), admDto.getEmail(), passwordEncoder.encode(admDto.getSenha()));
            gerenciadorRepository.save(adm);
            emailService.sendEmail(
                    adm.getEmail(),
                    "Bem-vindo(a) ao sistema da biblioteca JavaLirica",
                    "Olá " + adm.getNome() + ",\n\n" +
                            "Seu cadastro como gerenciador foi realizado com sucesso. Agora você tem acesso às funcionalidades administrativas do sistema da biblioteca.\n\n" +
                            "Se precisar de ajuda, entre em contato com nossa equipe de suporte.\n\n" +
                            "Atenciosamente,\n" +
                            "Equipe da Biblioteca"
            );
            return adm;
        } catch (Exception e) {
            throw new DataBaseException("Erro ao tentar criar o administrador.", e);
        }


    }

    @Transactional
    public GerenciadorBase criarGerenciador(GerenciadorBaseDTO gerenciadorBase) {

        if (gerenciadorRepository.existsByEmail(gerenciadorBase.getEmail())) {
            throw new GerenciadorJaExistenteException("Usuário já cadastrado");
        }

        switch (gerenciadorBase.getRole()) {
            case ADMIN:
                GerenciadorBase gerenciadorAdmin = new AdminGerenciador(gerenciadorBase.getNome(),
                        gerenciadorBase.getEmail(), passwordEncoder.encode(gerenciadorBase.getSenha()));
                gerenciadorRepository.save(gerenciadorAdmin);
                return gerenciadorAdmin;
            case SUBADMIN:
                GerenciadorBase gerenciadorSub = new SubAdminGerenciador(gerenciadorBase.getNome(),
                        gerenciadorBase.getEmail(), passwordEncoder.encode(gerenciadorBase.getSenha()));
                gerenciadorRepository.save(gerenciadorSub);
                return gerenciadorSub;
            default:
                throw new RoleInvalidaException("Role inválida");
        }
    }

}