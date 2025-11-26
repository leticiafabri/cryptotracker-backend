package com.cryptotracker.controller;

import com.cryptotracker.dto.TransacaoDTO;
import com.cryptotracker.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> criar(@RequestBody TransacaoDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping("/carteira/{carteiraId}")
    public List<TransacaoDTO> listarPorCarteira(@PathVariable Long carteiraId) {
        return service.listarPorCarteira(carteiraId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
