package com.cryptotracker.controller;
import com.cryptotracker.model.Transacao;
import com.cryptotracker.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
    private final TransacaoService service;
    public TransacaoController(TransacaoService service){this.service=service;}
    @PostMapping public ResponseEntity<Transacao> criar(@RequestBody Transacao t){ return ResponseEntity.ok(service.criar(t)); }
    @GetMapping("/carteira/{carteiraId}") public List<Transacao> listarPorCarteira(@PathVariable Long carteiraId){ return service.listarPorCarteira(carteiraId); }
    @DeleteMapping("/{id}") public ResponseEntity<?> deletar(@PathVariable Long id){ service.deletar(id); return ResponseEntity.ok().build(); }
}
