package br.com.javalirica.service;

import br.com.javalirica.domain.AdminGerenciador;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.domain.SubAdminGerenciador;
import br.com.javalirica.dto.gerenciador.GerenciadorBaseRequestDTO;
import br.com.javalirica.dto.gerenciador.GerenciadorResponseDTO;
import br.com.javalirica.enums.Roles;
import br.com.javalirica.repository.GerenciadorRepository;
import br.com.javalirica.service.exception.DataBaseException;
import br.com.javalirica.service.exception.GerenciadorJaExistenteException;
import br.com.javalirica.service.exception.RoleInvalidaException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<GerenciadorResponseDTO> buscarTodos(){
        List<GerenciadorBase> gerenciadoresBase = gerenciadorRepository.findAll();
        return toListGerenciadoresResponse(gerenciadoresBase);
    }
    
    @Transactional
    public GerenciadorResponseDTO primeiroAcesso(GerenciadorBaseRequestDTO admDto) throws DataBaseException {
        List<GerenciadorBase>gerenciadoresAdmin = gerenciadorRepository.findAllByRole(Roles.ADMIN);

        //Acredito que essa válidação tenha mesmo que está aqui, pois essa validação é apenas do primeiro acesso, caso contrario dê sugestões
        if (!gerenciadoresAdmin.isEmpty()) {
            throw new GerenciadorJaExistenteException("esté não é o primeiro acesso," +
                    " Já existe um administrador cadastrado no sistema.");
        }

        try {
            // Acho que essa linha pode ser refatorando criando um construtor no AdminGerenciador que recebe a superclass GerenciadorBase, o que acha?
            GerenciadorBase adm = new AdminGerenciador(admDto.getNome(), admDto.getEmail(), passwordEncoder.encode(admDto.getSenha()));
            gerenciadorRepository.save(adm);

            //Futuramente impl mensageria, e-mails com demora na requisição (essa parte pode ignorar pois farei posteriormente)
            emailService.sendEmail(
                    adm.getEmail(),
                    "Bem-vindo(a) ao sistema da biblioteca JavaLirica",
                    "Olá " + adm.getNome() + ",\n\n" +
                            "Seu cadastro como gerenciador foi realizado com sucesso. Agora você tem acesso às funcionalidades administrativas do sistema da biblioteca.\n\n" +
                            "Se precisar de ajuda, entre em contato com nossa equipe de suporte.\n\n" +
                            "Atenciosamente,\n" +
                            "Equipe da Biblioteca"
            );
            return toGerenciadorBaseDTO(adm);
        } catch (DataAccessException e) {
            //Acho que posso usar uma exceção mais expecifica
            throw new DataBaseException("Erro ao tentar criar o administrador.", e);
        }

    }

    public List<GerenciadorResponseDTO> buscarPorNome(String nome){
        if (nome == null || nome.isEmpty()) {
            throw new NullPointerException("nome inválido ou nulo");
        }
        List<GerenciadorBase> gerenciador = gerenciadorRepository.findByNomeContaining(nome.trim());
        if (gerenciador == null){
            throw new NullPointerException("Gerenciador não encontrado pelo nome: " + nome);
        }

        return toListGerenciadoresResponse(gerenciador);
    }

    @Transactional
    public GerenciadorResponseDTO criarGerenciador(GerenciadorBaseRequestDTO gerenciadorBase) {

        if (gerenciadorRepository.existsByEmail(gerenciadorBase.getEmail())) {
            throw new GerenciadorJaExistenteException("Usuário já cadastrado");
        }
        switch (gerenciadorBase.getRole()) {
            case ADMIN:
                GerenciadorBase gerenciadorAdmin = new AdminGerenciador(gerenciadorBase.getNome(),
                        gerenciadorBase.getEmail(), passwordEncoder.encode(gerenciadorBase.getSenha()));
                gerenciadorRepository.save(gerenciadorAdmin);
                return toGerenciadorBaseDTO(gerenciadorAdmin);
            case SUBADMIN:
                GerenciadorBase gerenciadorSub = new SubAdminGerenciador(gerenciadorBase.getNome(),
                        gerenciadorBase.getEmail(), passwordEncoder.encode(gerenciadorBase.getSenha()));
                gerenciadorRepository.save(gerenciadorSub);
                return toGerenciadorBaseDTO(gerenciadorSub);
            default:
                throw new RoleInvalidaException("Role inválida");
        }
    }

    @Transactional
    public void alterarSenha(String senha){

    }

    // impl mapper posteriormente 
    private List<GerenciadorResponseDTO> toListGerenciadoresResponse(List<GerenciadorBase> gerenciadorList) {
        return gerenciadorList.stream()
                .map(g -> new GerenciadorResponseDTO(g.getNome(), g.getRole(), g.getEmail()))
                .toList();
    }

    // impl mapper posteriormente
    private GerenciadorResponseDTO toGerenciadorBaseDTO(GerenciadorBase gerenciador){
        return new GerenciadorResponseDTO(gerenciador.getNome(),gerenciador.getRole(),gerenciador.getEmail());
    }

}