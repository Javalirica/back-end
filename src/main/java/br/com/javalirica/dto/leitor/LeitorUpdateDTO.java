package br.com.javalirica.dto.leitor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeitorUpdateDTO {

	private String nome;

	@Email
	private String email;

	@Size(min = 11, max = 11)
	private String celular;


}
