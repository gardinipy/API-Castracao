package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


/*
Original:
public record InsumoDTO(@NotBlank String nome, @NotNull String tipo,
                        @NotBlank double precoCusto, int qtdeEstoque) { }
*/
public record InsumoDTO(
    @NotBlank @Size(max = 100) String nome,
    @NotBlank @Size(max = 100) String tipo, // Alterado de @NotNull para @NotBlank
    @Min(0) double precoCusto, // Preço não pode ser negativo
    @Min(0) int qtdeEstoque    // Estoque não pode ser negativo
) {
}
