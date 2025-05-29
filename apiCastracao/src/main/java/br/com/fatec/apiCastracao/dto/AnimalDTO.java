package br.com.fatec.apiCastracao.dto;

import br.com.fatec.apiCastracao.modelo.Tutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AnimalDTO(@NotBlank String nome, @NotNull String especie,
                        String cor, int idade, String porte, Tutor tutor) {
}
