package com.cryptotracker.controller;

import com.cryptotracker.dto.TransferenciaDTO;
import com.cryptotracker.model.Transferencia;
import com.cryptotracker.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransferenciaDTO> transferir(@RequestBody TransferenciaDTO dto) {

        Transferencia entity = dtoToEntity(dto);

        Transferencia saved = service.transferir(entity);

        return ResponseEntity.ok(entityToDto(saved));
    }

    @GetMapping("/carteira/{carteiraId}")
    public List<TransferenciaDTO> listarPorCarteira(@PathVariable Long carteiraId) {
        return service.listarPorCarteira(carteiraId)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private Transferencia dtoToEntity(TransferenciaDTO dto) {
        Transferencia t = new Transferencia();
        t.setId(dto.getId());
        t.setCarteiraOrigem(dto.getCarteiraOrigem());
        t.setCarteiraDestino(dto.getCarteiraDestino());
        t.setMoedaId(dto.getMoedaId());
        t.setQuantidade(dto.getQuantidade());
        return t;
    }

    private TransferenciaDTO entityToDto(Transferencia t) {
        TransferenciaDTO dto = new TransferenciaDTO();
        dto.setId(t.getId());
        dto.setCarteiraOrigem(t.getCarteiraOrigem());
        dto.setCarteiraDestino(t.getCarteiraDestino());
        dto.setMoedaId(t.getMoedaId());
        dto.setQuantidade(t.getQuantidade());
        return dto;
    }
}
