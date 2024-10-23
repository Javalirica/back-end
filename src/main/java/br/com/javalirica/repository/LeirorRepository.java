package br.com.javalirica.repository;

import br.com.javalirica.domain.Leitor;
import br.com.javalirica.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeirorRepository extends JpaRepository<Leitor,Long> {
}
