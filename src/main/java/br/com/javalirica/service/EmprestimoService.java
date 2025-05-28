package br.com.javalirica.service;

import br.com.javalirica.domain.Emprestimo;
import br.com.javalirica.domain.Leitor;
import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.emprestimo.EmprestimoRequestDTO;
import br.com.javalirica.repository.EmprestimoRepository;
import br.com.javalirica.repository.LeitorRepository;
import br.com.javalirica.repository.LivroRepository;
import br.com.javalirica.service.exception.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final LeitorRepository leitorRepository;
    private final EmailService emailService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository, LeitorRepository leitorRepository, EmailService emailService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.leitorRepository = leitorRepository;
        this.emailService = emailService;
    }

    public List<Emprestimo> consultarEmprestimosNaoDevolvidos() {
        return emprestimoRepository.findByDevolvidoFalse();
    }

    public List<Emprestimo> buscarEmprestimo(String cpf) {
        Leitor leitor = leitorRepository.findByCpf(cpf);

        if (leitor == null) {
            // Tratar o caso onde o leitor não foi encontrado
            throw new LeitorNaoEncontradoException("Leitor não encontrado");
        }

        List<Emprestimo> emprestimos = emprestimoRepository.findEmprestimosNaoDevolvidosPorLeitor(leitor.getId());
        return emprestimos;
    }

    //Melhorar minha aquitetura dá pra deixar mais legivel
    @Transactional
    public Emprestimo emprestarLivro(EmprestimoRequestDTO emprestimoRequestDTO) {

        Leitor leitorObj = Optional.ofNullable(leitorRepository.findByCpf(emprestimoRequestDTO.getCpf()))
                .orElseThrow(() -> new EmprestimoInvalidoException("Leitor não encontrado para o CPF fornecido"));

        Livro livroObj = livroRepository.findByCodigoLivro(emprestimoRequestDTO.getCodigoLivro())
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado para o ID fornecido"));

        if (leitorObj.isBloqueado()) {
            throw new EmprestimoInvalidoException("Usuário está bloqueado e não pode pegar livros emprestado");
        }

        if (!livroObj.isDisponivel()) {
            throw new EmprestimoInvalidoException("Livro indisponível para empréstimo");
        }

        Emprestimo emprestimo = new Emprestimo(livroObj, leitorObj, LocalDate.now(), LocalDate.now().plusMonths(1));

        try {
            emprestimoRepository.save(emprestimo);
            livroObj.setDisponivel(false);
            livroRepository.save(livroObj);
            emailService.sendEmail(
                    leitorObj.getEmail(),
                    "Confirmação de Empréstimo de Livro",
                    "Olá " + leitorObj.getNome() + ",\n\n" +
                            "O empréstimo do livro \"" + livroObj.getNome() + "\" foi realizado com sucesso!.\n\n" +
                            "Por favor, lembre-se de que a devolução deverá ser feita até o dia " +
                            emprestimo.getDataLimiteEntrega() + ".\n\n" +
                            "Caso tenha dúvidas ou precise de ajuda, não hesite em nos contatar.\n\n" +
                            "Atenciosamente,\n" +
                            "Equipe da Biblioteca"
            );
            return emprestimo;
        } catch (DataBaseException e) {
            throw new DataBaseException("falha ao registrar emprestimo");
        }
    }

    @Transactional
    public void registrarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException
                        ("Empréstimo não encontrado, favor informar um ID de empréstimo válido."));

        if (!emprestimo.isDevolvido()) {
            emprestimo.setDevolvido(true);
            emprestimo.setDataDeEntrega(LocalDate.now());
            Livro livro = emprestimo.getLivro();
            livro.setDisponivel(true);

            livroRepository.save(livro);
            emprestimoRepository.save(emprestimo);
        } else {
            throw new EmprestimoInvalidoException("Este empréstimo já foi devolvido.");
        }
    }

}