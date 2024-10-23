package br.com.javalirica.repository;

import br.com.javalirica.domain.Emprestimo;
import br.com.javalirica.domain.Gerenciador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
