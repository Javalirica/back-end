package br.com.javalirica.service;

import br.com.javalirica.domain.Emprestimo;
import br.com.javalirica.domain.Leitor;
import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.EmprestimoDto;
import br.com.javalirica.repository.EmprestimoRepository;
import br.com.javalirica.repository.LeitorRepository;
import br.com.javalirica.repository.LivroRepository;
import br.com.javalirica.service.exception.EmprestimoInvalidoException;
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

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository, LeitorRepository leitorRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.leitorRepository = leitorRepository;
    }

    public List<Emprestimo> consultarTodosEmprestimos(){

        return emprestimoRepository.findAll();
    }

    @Transactional
    public Emprestimo emprestarLivro(EmprestimoDto emprestimoDto) {

        Leitor leitorObj = Optional.ofNullable(leitorRepository.findByCpf(emprestimoDto.getCpf()))
                .orElseThrow(() -> new EmprestimoInvalidoException("Leitor não encontrado para o CPF fornecido"));

        Livro livroObj = livroRepository.findByCodigoLivro(emprestimoDto.getCodigoLivro())
                .orElseThrow(() -> new EmprestimoInvalidoException("Livro não encontrado para o ID fornecido"));

        if (leitorObj.isBloqueado()) {
            throw new EmprestimoInvalidoException("Usuário está bloqueado e não pode pegar livros emprestado");
        }

        if (!livroObj.isDisponivel()) {
            throw new EmprestimoInvalidoException("Livro indisponível para empréstimo");
        }

        Emprestimo emprestimo = new Emprestimo(livroObj, leitorObj, LocalDate.now(), LocalDate.now().plusMonths(1));

        emprestimoRepository.save(emprestimo);
        livroObj.setDisponivel(false);
        livroRepository.save(livroObj);
        return emprestimo;
    }
}
