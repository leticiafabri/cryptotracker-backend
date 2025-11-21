package com.cryptotracker.controller;
import com.cryptotracker.model.Carteira;
import com.cryptotracker.service.CarteiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/carteiras")
public class CarteiraController {
    private final CarteiraService service;
    public CarteiraController(CarteiraService service){this.service=service;}
    @PostMapping public ResponseEntity<Carteira> criar(@RequestBody Carteira c){ return ResponseEntity.ok(service.criar(c)); }
    @GetMapping("/usuario/{userId}") public List<Carteira> listarPorUsuario(@PathVariable Long userId){ return service.listarPorUsuario(userId); }
    @GetMapping("/{id}") public Carteira get(@PathVariable Long id){ return service.buscar(id); }
    @DeleteMapping("/{id}") public ResponseEntity<?> deletar(@PathVariable Long id){ service.deletar(id); return ResponseEntity.ok().build(); }
}
