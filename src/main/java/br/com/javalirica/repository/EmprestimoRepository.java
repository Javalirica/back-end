package br.com.javalirica.repository;

import br.com.javalirica.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByDevolvidoFalse();
}
