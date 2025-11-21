package com.cryptotracker.controller;
import com.cryptotracker.model.Moeda;
import com.cryptotracker.service.MoedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/moedas")
public class MoedaController {
    private final MoedaService service;
    public MoedaController(MoedaService service){this.service=service;}
    @GetMapping public List<Moeda> all(){ return service.listar(); }
    @GetMapping("/{id}") public Moeda get(@PathVariable Long id){ return service.buscar(id); }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping public ResponseEntity<Moeda> create(@RequestBody Moeda m){ return ResponseEntity.ok(service.criar(m)); }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}") public ResponseEntity<Moeda> update(@PathVariable Long id, @RequestBody Moeda m){ return ResponseEntity.ok(service.atualizar(id,m)); }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.deletar(id); return ResponseEntity.ok().build(); }
}
