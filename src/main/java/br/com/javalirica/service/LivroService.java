package br.com.javalirica.service;

import br.com.javalirica.dto.livro.LivroRequestDTO;
import br.com.javalirica.mapper.LivroMapper;
import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.livro.LivroResponseDTO;
import br.com.javalirica.repository.LivroRepository;
import br.com.javalirica.service.exception.DataBaseException;
import br.com.javalirica.service.exception.LivroInvalidoException;
import br.com.javalirica.service.exception.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper mapper;

    public List<LivroResponseDTO> listarTodos (){

        return mapper.toListResponse(livroRepository.findAll());
    }

    public List<LivroResponseDTO> buscarPorNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<LivroResponseDTO> livros = mapper.toListResponse(livroRepository.findByNomeContaining(nome.trim()));
        return livros.isEmpty() ? Collections.emptyList() : livros;
    }

    @Transactional
    public LivroResponseDTO adicionarLivro (LivroRequestDTO livro) {

        if (livro == null){
            throw new LivroInvalidoException("O livro não pode ser nulo");
        }
        try {
            Livro livroEntity = livroRepository.save(mapper.toEntity(livro));
            return  mapper.toRespose(livroEntity);

        } catch (Exception e) {
            throw  new DataBaseException("Erro ao salvar o livro no banco de dados");
        }

    }

    @Transactional
    public void removerLivro (String codigoLivro){
        if (codigoLivro == null) {
            throw new LivroInvalidoException("O codigo do livro não pode ser nulo");
        }
        try {
            Livro livro = livroRepository.findByCodigoLivro(codigoLivro).orElseThrow(() ->
                    new LivroNaoEncontradoException("Livro não encontrado pelo codigo: " + codigoLivro));
            livroRepository.deleteById(livro.getId());
        } catch (DataIntegrityViolationException e ) {
            throw new DataBaseException("Erro ao excluir Livro");
        }
    }
}
