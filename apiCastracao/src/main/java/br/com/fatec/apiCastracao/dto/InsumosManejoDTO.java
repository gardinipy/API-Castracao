package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

// Original: public record InsumosManejoDTO() { }
public record InsumosManejoDTO(
    @NotNull Integer insumoId, // ID do Insumo relacionado
    @NotNull Integer manejoId,   // ID do Manejo relacionado
    @NotNull @Min(1) Integer qtde       // Quantidade não pode ser nula e deve ser pelo menos 1.
) {
}
