package com.cryptotracker.service;
import com.cryptotracker.model.Moeda;
import com.cryptotracker.repository.MoedaRepository;
import com.cryptotracker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MoedaService {
    private final MoedaRepository repo;
    public MoedaService(MoedaRepository repo){this.repo=repo;}
    public Moeda criar(Moeda m){ return repo.save(m); }
    public List<Moeda> listar(){ return repo.findAll(); }
    public Moeda buscar(Long id){ return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("ERR_MOEDA_NOT_FOUND","Moeda não encontrada")); }
    public Moeda atualizar(Long id, Moeda m){ Moeda ex = buscar(id); ex.setNome(m.getNome()); ex.setSimbolo(m.getSimbolo()); return repo.save(ex); }
    public void deletar(Long id){ Moeda ex = buscar(id); repo.delete(ex); }
}
