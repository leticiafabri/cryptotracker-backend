package com.cryptotracker.controller;
import com.cryptotracker.model.CarteiraMoeda;
import com.cryptotracker.service.CarteiraMoedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/carteira-moedas")
public class CarteiraMoedaController {
    private final CarteiraMoedaService service;
    public CarteiraMoedaController(CarteiraMoedaService service){this.service=service;}
    @PostMapping
    public ResponseEntity<CarteiraMoeda> adicionar(@RequestBody CarteiraMoedaDTO dto)
    {
        return ResponseEntity.ok(service.adicionar(dto.carteiraId(), dto.moedaId(), dto.quantidade()));
    }
    @GetMapping("/carteira/{carteiraId}") public List<CarteiraMoeda> listarPorCarteira(@PathVariable Long carteiraId){ return service.listarPorCarteira(carteiraId); }
    @DeleteMapping("/{id}") public ResponseEntity<?> remover(@PathVariable Long id){ service.remover(id); return ResponseEntity.ok().build(); }
    @PostMapping("/favorito") public ResponseEntity<CarteiraMoeda> alternarFavorito(@RequestParam Long carteiraId, @RequestParam Long moedaId){ return ResponseEntity.ok(service.alternarFavorito(carteiraId, moedaId)); }
}
