package br.com.javalirica.dto;

import br.com.javalirica.domain.GerenciadorBase;
import br.com.javalirica.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GerenciadorBaseRequestDTO {

    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "O papel (role) não pode ser nulo")
    private Roles role;

    @Email(message = "Email deve ser válido")
    @NotNull(message = "O email não pode ser nulo")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    private String senha;

    public GerenciadorBaseRequestDTO(GerenciadorBase gerenciador) {
        this.nome = gerenciador.getNome();
        this.role = gerenciador.getRole();
        this.email = gerenciador.getEmail();
        this.senha = gerenciador.getSenha();
    }

}
