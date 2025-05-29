package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

public record MovimentacaoDTO(@NotBlank String origem, @NotBlank Date data,
                              @NotBlank int quantidade) {
}
