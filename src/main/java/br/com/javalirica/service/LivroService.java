package br.com.javalirica.service;

import br.com.javalirica.domain.Livro;
import br.com.javalirica.dto.LivroDto;
import br.com.javalirica.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public LivroDto buscarPorNome(String nome) {
        if (!nome.isEmpty()) {
            Optional<Livro> livro = livroRepository.findByNome(nome);

            if (livro.isPresent()) {
                return new LivroDto(livro.get());

            }
        }

        // to-do exception
        return null;
    }
}
