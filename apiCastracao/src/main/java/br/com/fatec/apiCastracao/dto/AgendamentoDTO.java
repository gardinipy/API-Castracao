package br.com.fatec.apiCastracao.dto;

import br.com.fatec.apiCastracao.modelo.Animal;
import br.com.fatec.apiCastracao.modelo.Responsavel;
import jakarta.validation.constraints.NotBlank;


import java.util.Date;

public record AgendamentoDTO(String observacoes, @NotBlank Date dataPrevista,
                             @NotBlank Animal animal, @NotBlank Responsavel responsavel) {
}
