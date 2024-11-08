package br.com.javalirica.repository;

import br.com.javalirica.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    List<Livro> findByNomeContaining(String nome);
}
