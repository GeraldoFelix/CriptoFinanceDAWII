package br.com.cryptofinance.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriptomoedaRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String descricao;
}

