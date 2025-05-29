package br.com.fatec.apiCastracao.dto;

// import br.com.fatec.apiCastracao.modelo.Tutor; // Removido
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


/*
Original:
public record AnimalDTO(@NotBlank String nome, @NotNull String especie,
                        String cor, int idade, String porte, Tutor tutor) { }
*/
public record AnimalDTO(
    @NotBlank @Size(max = 100) String nome,
    @NotBlank @Size(max = 100) String especie, // Alterado de @NotNull para @NotBlank
    @Size(max = 80) String cor,
    @Min(0) int idade, // Idade não pode ser negativa
    @NotBlank @Size(max = 100) String porte, // Alterado para @NotBlank
    @NotNull Integer tutorId // Substituído Tutor por tutorId
) {
}
