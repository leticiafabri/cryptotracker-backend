package com.cryptotracker.service;
import com.cryptotracker.model.Transacao;
import com.cryptotracker.repository.TransacaoRepository;
import com.cryptotracker.repository.CarteiraRepository;
import com.cryptotracker.repository.MoedaRepository;
import com.cryptotracker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TransacaoService {
    private final TransacaoRepository repo;
    private final CarteiraRepository carteiraRepo;
    private final MoedaRepository moedaRepo;
    public TransacaoService(TransacaoRepository repo, CarteiraRepository carteiraRepo, MoedaRepository moedaRepo){ this.repo=repo;this.carteiraRepo=carteiraRepo;this.moedaRepo=moedaRepo;}
    public Transacao criar(Transacao t){
        carteiraRepo.findById(t.getCarteiraId()).orElseThrow(()->new ResourceNotFoundException("ERR_CARTEIRA_NOT_FOUND","Carteira não encontrada"));
        moedaRepo.findById(t.getMoedaId()).orElseThrow(()->new ResourceNotFoundException("ERR_MOEDA_NOT_FOUND","Moeda não encontrada"));
        return repo.save(t);
    }
    public List<Transacao> listarPorCarteira(Long carteiraId){ return repo.findByCarteiraId(carteiraId); }
    public void deletar(Long id){ repo.deleteById(id); }
}
