package br.com.javalirica.repository;

import br.com.javalirica.domain.GerenciadorBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GerenciadorRepository extends JpaRepository<GerenciadorBase, UUID> {

    boolean existsByEmail(String email);
    GerenciadorBase findByEmail(String email);
}
