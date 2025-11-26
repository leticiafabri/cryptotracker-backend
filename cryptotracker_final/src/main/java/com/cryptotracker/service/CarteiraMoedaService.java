package com.cryptotracker.service;

import com.cryptotracker.model.CarteiraMoeda;
import com.cryptotracker.repository.CarteiraMoedaRepository;
import com.cryptotracker.repository.MoedaRepository;
import com.cryptotracker.repository.CarteiraRepository;
import com.cryptotracker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraMoedaService {

    private final CarteiraMoedaRepository repo;
    private final CarteiraRepository carteiraRepo;
    private final MoedaRepository moedaRepo;

    public CarteiraMoedaService(
            CarteiraMoedaRepository repo,
            CarteiraRepository carteiraRepo,
            MoedaRepository moedaRepo
    ) {
        this.repo = repo;
        this.carteiraRepo = carteiraRepo;
        this.moedaRepo = moedaRepo;
    }

    public CarteiraMoeda adicionar(Long carteiraId, Long moedaId, double quantidade) {

        carteiraRepo.findById(carteiraId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ERR_CARTEIRA_NOT_FOUND", "Carteira não encontrada")
                );

        moedaRepo.findById(moedaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ERR_MOEDA_NOT_FOUND", "Moeda não encontrada")
                );

        var exists = repo.findByCarteiraIdAndMoedaId(carteiraId, moedaId);

        if (!exists.isEmpty()) {
            var c = exists.get(0);
            c.setQuantidade(c.getQuantidade() + quantidade);
            return repo.save(c);
        }

        CarteiraMoeda novo = new CarteiraMoeda(carteiraId, moedaId, quantidade);
        return repo.save(novo);
    }

    public List<CarteiraMoeda> listarPorCarteira(Long carteiraId) {
        return repo.findByCarteiraId(carteiraId);
    }

    public void remover(Long id) {
        repo.deleteById(id);
    }

    public CarteiraMoeda alternarFavorito(Long carteiraId, Long moedaId) {

        var list = repo.findByCarteiraIdAndMoedaId(carteiraId, moedaId);

        if (list.isEmpty())
            throw new ResourceNotFoundException("ERR_CM_NOT_FOUND", "Registro Carteira-Moeda não encontrado");

        var cm = list.get(0);
        cm.setFavorito(!cm.isFavorito());

        return repo.save(cm);
    }
}
