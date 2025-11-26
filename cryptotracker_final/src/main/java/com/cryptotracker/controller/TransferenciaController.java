package com.cryptotracker.controller;

import com.cryptotracker.dto.TransferenciaDTO;
import com.cryptotracker.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService service;

    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransferenciaDTO> transferir(@RequestBody TransferenciaDTO dto) {
        return ResponseEntity.ok(service.transferir(dto));
    }

    @GetMapping("/carteira/{carteiraId}")
    public List<TransferenciaDTO> listarPorCarteira(@PathVariable Long carteiraId) {
        return service.listarPorCarteira(carteiraId);
    }
}
