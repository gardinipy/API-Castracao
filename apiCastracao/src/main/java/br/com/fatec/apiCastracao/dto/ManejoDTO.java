package br.com.fatec.apiCastracao.dto;

import jakarta.validation.constraints.NotBlank;

public record ManejoDTO(@NotBlank String nome) {
}
