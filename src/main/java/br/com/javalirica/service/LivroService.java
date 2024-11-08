package br.com.javalirica.service;

import br.com.javalirica.domain.Livro;
import br.com.javalirica.repository.LivroRepository;
import br.com.javalirica.service.exception.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos (){
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<Livro> livros = livroRepository.findByNomeContaining(nome.trim());
        return livros.isEmpty() ? Collections.emptyList() : livros;
    }

//    public Livro adicionarLivro (Livro livro) {
//
//        if(livroRepository.findByNome(livro.getNome()).isEmpty()){
//            return livroRepository.save(livro);
//        } else {
//            // lançar exceção -todo
//        }
//        return null;
//    }

    public void removerLivro (Long id){
        if (id == null) {
            throw new NullPointerException("O ID do livro não pode ser nulo");
        }
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado pelo id: " + id));
        livroRepository.deleteById(livro.getId());
    }
}
