package com.cryptotracker.service;
import com.cryptotracker.model.Carteira;
import com.cryptotracker.repository.CarteiraRepository;
import com.cryptotracker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CarteiraService {
    private final CarteiraRepository repo;
    public CarteiraService(CarteiraRepository repo){this.repo=repo;}
    public Carteira criar(Carteira c){ return repo.save(c); }
    public List<Carteira> listarPorUsuario(Long userId){ return repo.findByUsuarioId(userId); }
    public Carteira buscar(Long id){ return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("ERR_CARTEIRA_NOT_FOUND","Carteira não encontrada")); }
    public void deletar(Long id){ Carteira ex = buscar(id); repo.delete(ex); }
}
