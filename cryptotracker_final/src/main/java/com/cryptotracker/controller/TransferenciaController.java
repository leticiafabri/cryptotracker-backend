package com.cryptotracker.controller;
import com.cryptotracker.model.Transferencia;
import com.cryptotracker.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    private final TransferenciaService service;
    public TransferenciaController(TransferenciaService service){this.service=service;}
    @PostMapping public ResponseEntity<Transferencia> transferir(@RequestBody Transferencia t){ return ResponseEntity.ok(service.transferir(t)); }
    @GetMapping("/carteira/{carteiraId}") public List<Transferencia> listarPorCarteira(@PathVariable Long carteiraId){ return service.listarPorCarteira(carteiraId); }
}
