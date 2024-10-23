package br.com.javalirica.repository;

import br.com.javalirica.domain.Gerenciador;
import br.com.javalirica.domain.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GerenciadorRepository extends JpaRepository<Gerenciador, UUID> {
}
