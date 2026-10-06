package br.com.cryptofinance.repository;

import br.com.cryptofinance.entity.Criptomoeda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CriptomoedaRepository extends JpaRepository<Criptomoeda, Integer> {
    Optional<Criptomoeda> findByNome(String nome);
}