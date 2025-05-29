package br.com.fatec.apiCastracao.dto;

// import br.com.fatec.apiCastracao.modelo.Animal; // Removido
// import br.com.fatec.apiCastracao.modelo.Responsavel; // Removido
import jakarta.validation.constraints.NotNull; // Mudança de NotBlank para NotNull para Date e IDs
import jakarta.validation.constraints.FutureOrPresent; // Exemplo de validação mais específica para data

import java.util.Date;

/*
Original:
public record AgendamentoDTO(String observacoes, @NotBlank Date dataPrevista,
                             @NotBlank Animal animal, @NotBlank Responsavel responsavel) { }
*/
public record AgendamentoDTO(
    String observacoes,
    @NotNull @FutureOrPresent Date dataPrevista, // Data deve ser presente ou futura
    @NotNull Integer animalId,
    @NotNull Integer responsavelId
) {
}
