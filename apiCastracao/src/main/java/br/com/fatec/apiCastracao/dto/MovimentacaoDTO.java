package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotNull; // Em vez de NotBlank para Date
import jakarta.validation.constraints.Min;    // Para quantidade
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent; // Exemplo de validação mais específica para data

import java.util.Date;

/*
Original:
public record MovimentacaoDTO(@NotBlank String origem, @NotBlank Date data,
                              @NotBlank int quantidade) { }
*/
public record MovimentacaoDTO(
    @Size(max = 120) String origem, // @NotBlank pode ser adicionado se 'origem' for obrigatório
    @NotNull @PastOrPresent Date data, // Data não pode ser futura
    @Min(1) int quantidade, // Quantidade deve ser positiva
    @NotNull Integer insumoId // Adicionado para relacionar com Insumo
) {
}
