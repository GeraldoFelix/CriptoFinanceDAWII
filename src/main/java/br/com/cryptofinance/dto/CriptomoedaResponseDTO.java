package br.com.cryptofinance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CriptomoedaResponseDTO {
    private Integer idCripto;
    private String nome;
    private String descricao;
}
