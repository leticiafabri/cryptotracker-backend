package com.cryptotracker.controller;

import com.cryptotracker.dto.MoedaDTO;
import com.cryptotracker.service.MoedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moedas")
public class MoedaController {

    private final MoedaService service;

    public MoedaController(MoedaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MoedaDTO> all() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public MoedaDTO get(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<MoedaDTO> create(@RequestBody MoedaDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MoedaDTO> update(@PathVariable Long id, @RequestBody MoedaDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
