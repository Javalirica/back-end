package br.com.javalirica.service;

import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.LivroDto;
import br.com.javalirica.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;


    public List<Livro> listarTodos (){
       return livroRepository.findAll();
    }
    public Livro buscarPorNome(String nome) {
        if (!nome.isEmpty()) {
            Optional<Livro> livro = livroRepository.findByNome(nome);

            if (livro.isPresent()) {
                return  livro.get();
            }
        }
        // to-do exception
        return null;
    }

    public Livro adicionarLivro (Livro livro) {
        if(livroRepository.findByNome(livro.getNome()).isEmpty()){
            return livroRepository.save(livro);
        } else {
            // lançar exceção -todo
        }
        return null;
    }

    public void removerLivro (String nome){

        Optional<Livro> livro =  livroRepository.findByNome(nome);
       livroRepository.delete(livro.get());
    }
}
