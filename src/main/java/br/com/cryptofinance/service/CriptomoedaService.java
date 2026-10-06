package br.com.cryptofinance.service;

import br.com.cryptofinance.dto.CriptomoedaRequestDTO;
import br.com.cryptofinance.dto.CriptomoedaResponseDTO;
import br.com.cryptofinance.entity.Criptomoeda;
import br.com.cryptofinance.exception.RegraDeNegocioException;
import br.com.cryptofinance.repository.CriptomoedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriptomoedaService {

    @Autowired
    private CriptomoedaRepository criptomoedaRepository;

    // CREATE
    @Transactional
    public CriptomoedaResponseDTO criar(CriptomoedaRequestDTO dto) {
        if (criptomoedaRepository.findByNome(dto.getNome()).isPresent()) {
            throw new RegraDeNegocioException("Já existe uma criptomoeda com esse nome");
        }

        Criptomoeda cripto = new Criptomoeda();
        cripto.setNome(dto.getNome());
        cripto.setDescricao(dto.getDescricao());

        cripto = criptomoedaRepository.save(cripto);
        return toResponseDTO(cripto);
    }

    // READ (um)
    public CriptomoedaResponseDTO buscarPorId(Integer id) {
        Criptomoeda cripto = criptomoedaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Criptomoeda não encontrada"));
        return toResponseDTO(cripto);
    }

    // READ (todos)
    public List<CriptomoedaResponseDTO> listarTodas() {
        return criptomoedaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Transactional
    public CriptomoedaResponseDTO atualizar(Integer id, CriptomoedaRequestDTO dto) {
        Criptomoeda cripto = criptomoedaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Criptomoeda não encontrada"));

        cripto.setNome(dto.getNome());
        cripto.setDescricao(dto.getDescricao());

        cripto = criptomoedaRepository.save(cripto);
        return toResponseDTO(cripto);
    }

    // DELETE
    @Transactional
    public void deletar(Integer id) {
        if (!criptomoedaRepository.existsById(id)) {
            throw new RegraDeNegocioException("Criptomoeda não encontrada");
        }
        criptomoedaRepository.deleteById(id);
    }

    private CriptomoedaResponseDTO toResponseDTO(Criptomoeda cripto) {
        return new CriptomoedaResponseDTO(
                cripto.getIdCripto(),
                cripto.getNome(),
                cripto.getDescricao()
        );
    }
}