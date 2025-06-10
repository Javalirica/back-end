package br.com.javalirica.repository;

import br.com.javalirica.domain.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeitorRepository extends JpaRepository<Leitor,Long> {

    Leitor findByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByCelular(String celular);
    boolean existsByCpf(String cpf);
}
