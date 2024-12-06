package br.com.javalirica.repository;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GerenciadorRepository extends JpaRepository<GerenciadorBase, UUID> {

    boolean existsByEmail(String email);
    GerenciadorBase findByEmail(String email);

    List<GerenciadorBase> findAllByRole(Roles role);

    GerenciadorBase findByNomeContaining(String nome);
}