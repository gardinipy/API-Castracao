package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsumoDTO(@NotBlank String nome, @NotNull String tipo,
                        @NotBlank double precoCusto, int qtdeEstoque) {
}
