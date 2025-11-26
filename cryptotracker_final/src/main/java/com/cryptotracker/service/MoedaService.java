package com.cryptotracker.service;

import com.cryptotracker.dto.MoedaDTO;
import com.cryptotracker.exception.ResourceNotFoundException;
import com.cryptotracker.model.Moeda;
import com.cryptotracker.repository.MoedaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoedaService {

    private final MoedaRepository repo;

    public MoedaService(MoedaRepository repo) {
        this.repo = repo;
    }

//aqui faz as conversoes

    private MoedaDTO toDTO(Moeda m) {
        return new MoedaDTO(
                m.getId(),
                m.getNome(),
                m.getSimbolo()
        );
    }

    private Moeda toEntity(MoedaDTO dto) {
        Moeda m = new Moeda();
        m.setId(dto.id());
        m.setNome(dto.nome());
        m.setSimbolo(dto.simbolo());
        return m;
    }

    //métodos 

    public MoedaDTO criar(MoedaDTO dto) {
        Moeda entidade = toEntity(dto);
        Moeda salvo = repo.save(entidade);
        return toDTO(salvo);
    }

    public List<MoedaDTO> listar() {
        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public MoedaDTO buscar(Long id) {
        Moeda m = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ERR_MOEDA_NOT_FOUND",
                                "Moeda não encontrada"
                        ));
        return toDTO(m);
    }

    public MoedaDTO atualizar(Long id, MoedaDTO dto) {
        Moeda existente = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ERR_MOEDA_NOT_FOUND",
                                "Moeda não encontrada"
                        ));

        existente.setNome(dto.nome());
        existente.setSimbolo(dto.simbolo());

        Moeda atualizado = repo.save(existente);
        return toDTO(atualizado);
    }

    public void deletar(Long id) {
        Moeda existente = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "ERR_MOEDA_NOT_FOUND",
                                "Moeda não encontrada"
                        ));
        repo.delete(existente);
    }
}
