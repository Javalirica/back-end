package br.com.javalirica.repository;

import br.com.javalirica.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByDevolvidoFalse();
    @Query("SELECT e FROM Emprestimo e WHERE e.leitor.id = :leitorId AND e.devolvido = false")
    List<Emprestimo> findEmprestimosNaoDevolvidosPorLeitor(@Param("leitorId") Long leitorId);

}
