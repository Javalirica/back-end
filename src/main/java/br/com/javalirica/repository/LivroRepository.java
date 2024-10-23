package br.com.javalirica.repository;

import br.com.javalirica.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Long> {
}
