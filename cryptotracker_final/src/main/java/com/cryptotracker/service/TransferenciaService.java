package com.cryptotracker.service;
import com.cryptotracker.model.Transferencia;
import com.cryptotracker.model.CarteiraMoeda;
import com.cryptotracker.repository.TransferenciaRepository;
import com.cryptotracker.repository.CarteiraRepository;
import com.cryptotracker.repository.CarteiraMoedaRepository;
import com.cryptotracker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TransferenciaService {
    private final TransferenciaRepository repo;
    private final CarteiraRepository carteiraRepo;
    private final CarteiraMoedaRepository carteiraMoedaRepo;
    public TransferenciaService(TransferenciaRepository repo, CarteiraRepository carteiraRepo, CarteiraMoedaRepository carteiraMoedaRepo){
        this.repo=repo; this.carteiraRepo=carteiraRepo; this.carteiraMoedaRepo=carteiraMoedaRepo;
    }
    public Transferencia transferir(Transferencia t){
        var origem = carteiraRepo.findById(t.getCarteiraOrigem()).orElseThrow(()->new ResourceNotFoundException("ERR_CARTEIRA_NOT_FOUND","Carteira origem não encontrada"));
        var destino = carteiraRepo.findById(t.getCarteiraDestino()).orElseThrow(()->new ResourceNotFoundException("ERR_CARTEIRA_NOT_FOUND","Carteira destino não encontrada"));
        if(!origem.getUsuarioId().equals(destino.getUsuarioId())) throw new RuntimeException("Transferência só entre carteiras do mesmo usuário permitida");
        var listOrig = carteiraMoedaRepo.findByCarteiraIdAndMoedaId(t.getCarteiraOrigem(), t.getMoedaId());
        if(listOrig.isEmpty()) throw new ResourceNotFoundException("ERR_CM_NOT_FOUND","Carteira origem não tem essa moeda");
        var cmOrig = listOrig.get(0);
        if(cmOrig.getQuantidade() < t.getQuantidade()) throw new RuntimeException("Saldo insuficiente");
        cmOrig.setQuantidade(cmOrig.getQuantidade() - t.getQuantidade());
        carteiraMoedaRepo.save(cmOrig);
        var listDest = carteiraMoedaRepo.findByCarteiraIdAndMoedaId(t.getCarteiraDestino(), t.getMoedaId());
        if(listDest.isEmpty()){
            carteiraMoedaRepo.save(new CarteiraMoeda(t.getCarteiraDestino(), t.getMoedaId(), t.getQuantidade()));
        } else {
            var cmDest = listDest.get(0);
            cmDest.setQuantidade(cmDest.getQuantidade() + t.getQuantidade());
            carteiraMoedaRepo.save(cmDest);
        }
        return repo.save(t);
    }
    public List<Transferencia> listarPorCarteira(Long carteiraId){ return repo.findByCarteiraOrigemOrCarteiraDestino(carteiraId, carteiraId); }
}
