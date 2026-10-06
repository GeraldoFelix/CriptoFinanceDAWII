package br.com.cryptofinance.controller;

import br.com.cryptofinance.dto.CriptomoedaRequestDTO;
import br.com.cryptofinance.dto.CriptomoedaResponseDTO;
import br.com.cryptofinance.service.CriptomoedaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criptomoedas")
public class CriptomoedaController {

    @Autowired
    private CriptomoedaService criptomoedaService;

    @PostMapping
    public ResponseEntity<CriptomoedaResponseDTO> criar(@Valid @RequestBody CriptomoedaRequestDTO dto) {
        CriptomoedaResponseDTO response = criptomoedaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriptomoedaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(criptomoedaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CriptomoedaResponseDTO>> listar() {
        return ResponseEntity.ok(criptomoedaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriptomoedaResponseDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CriptomoedaRequestDTO dto) {
        return ResponseEntity.ok(criptomoedaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        criptomoedaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
