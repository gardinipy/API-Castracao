package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotNull;

// Original: public record ItensManejoDTO() { }
public record ItensManejoDTO(
    @NotNull Integer agendamentoId, // ID do Agendamento relacionado
    @NotNull Integer manejoId       // ID do Manejo relacionado
) {
}
