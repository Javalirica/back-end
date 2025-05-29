package br.com.javalirica.service;

import br.com.javalirica.Mapper.GerenciadorMapper;
import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.dto.gerenciador.GerenciadorBaseRequestDTO;
import br.com.javalirica.dto.gerenciador.GerenciadorResponseDTO;
import br.com.javalirica.enums.Roles;
import br.com.javalirica.repository.GerenciadorRepository;
import br.com.javalirica.service.exception.DataBaseException;
import br.com.javalirica.service.exception.GerenciadorJaExistenteException;
import br.com.javalirica.service.exception.GerenciadorNaoEncontradoException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GerenciadorService {

    private final GerenciadorRepository gerenciadorRepository;
    private final EmailService emailService;
    private final GerenciadorMapper mapper;

    public GerenciadorService(GerenciadorRepository gerenciadorRepository, EmailService emailService, GerenciadorMapper mapper) {
        this.gerenciadorRepository = gerenciadorRepository;
        this.emailService = emailService;
		this.mapper = mapper;
	}

    public List<GerenciadorResponseDTO> buscarTodos(){
        List<GerenciadorBase> gerenciadoresBase = gerenciadorRepository.findAll();
        return mapper.toResponseDTOList(gerenciadoresBase);
    }
    
    @Transactional
    public GerenciadorResponseDTO primeiroAcesso(GerenciadorBaseRequestDTO admDto) throws DataBaseException {
        List<GerenciadorBase>gerenciadoresAdmin = gerenciadorRepository.findAllByRole(Roles.ADMIN);

        if (!gerenciadoresAdmin.isEmpty()) {
            throw new GerenciadorJaExistenteException("esté não é o primeiro acesso," +
                    " Já existe um administrador cadastrado no sistema.");
        }

        try {

            GerenciadorBase adm = mapper.toGerenciadorEntity(admDto);
            gerenciadorRepository.save(adm);

            //Futuramente impl mensageria, e-mails com demora na requisição
            emailService.sendEmail(
                    adm.getEmail(),
                    "Bem-vindo(a) ao sistema da biblioteca JavaLirica",
                    "Olá " + adm.getNome() + ",\n\n" +
                            "Seu cadastro como gerenciador foi realizado com sucesso. Agora você tem acesso às funcionalidades administrativas do sistema da biblioteca.\n\n" +
                            "Se precisar de ajuda, entre em contato com nossa equipe de suporte.\n\n" +
                            "Atenciosamente,\n" +
                            "Equipe da Biblioteca"
            );
            return mapper.toResponseDTO(adm);

        } catch (DataAccessException e) {
            //Acho que posso usar uma exceção mais expecifica
            throw new DataBaseException("Erro ao tentar criar o administrador.", e);
        }

    }

    public List<GerenciadorResponseDTO> buscarPorNome(String nome){
        if (nome == null || nome.isEmpty()) {
            throw new GerenciadorNaoEncontradoException("nome inválido ou nulo");
        }
        List<GerenciadorBase> gerenciadores = gerenciadorRepository.findByNomeContaining(nome.trim());
        if (gerenciadores == null){
            throw new GerenciadorNaoEncontradoException("Gerenciador não encontrado pelo nome: " + nome);
        }

        return mapper.toResponseDTOList(gerenciadores);
    }

    @Transactional
    public GerenciadorResponseDTO criarGerenciador(GerenciadorBaseRequestDTO gerenciadorBase) {

        if (gerenciadorRepository.existsByEmail(gerenciadorBase.getEmail())) {
            throw new GerenciadorJaExistenteException("Usuário já cadastrado");
        }

        GerenciadorBase entity = mapper.toGerenciadorEntity(gerenciadorBase);
        gerenciadorRepository.save(entity);
        return mapper.toResponseDTO(entity);
    }

    //pensar na impl posteriormente
    @Transactional
    public void alterarSenha(String senha){}

}