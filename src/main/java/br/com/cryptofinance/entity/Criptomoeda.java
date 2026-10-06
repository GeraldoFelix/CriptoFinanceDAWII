package br.com.cryptofinance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "criptomoeda")
@Getter
@Setter
public class Criptomoeda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cripto")
    private Integer idCripto;

    @Column(unique = true, nullable = false)
    private String nome;

    private String descricao;
}