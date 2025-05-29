package br.com.fatec.apiCastracao.dto;

// import br.com.fatec.apiCastracao.modelo.Animal; // Removido
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/*
Original:
public record TutorDTO(@NotBlank String nome, @NotBlank String endereco,
                       @NotBlank String celular, List<Animal> animais) { }
*/
public record TutorDTO(
    @NotBlank @Size(max = 100) String nome,
    @NotBlank @Size(max = 250) String endereco,
    @NotBlank @Size(max = 16) String celular
    // List<Animal> animais foi removido para DTOs de entrada/criação
) {
}
