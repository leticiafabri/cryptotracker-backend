package com.cryptotracker.service;

import com.cryptotracker.dto.TransacaoDTO;
import com.cryptotracker.exception.ResourceNotFoundException;
import com.cryptotracker.model.Transacao;
import com.cryptotracker.repository.CarteiraRepository;
import com.cryptotracker.repository.MoedaRepository;
import com.cryptotracker.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository repo;
    private final CarteiraRepository carteiraRepo;
    private final MoedaRepository moedaRepo;

    public TransacaoService(
            TransacaoRepository repo,
            CarteiraRepository carteiraRepo,
            MoedaRepository moedaRepo
    ) {
        this.repo = repo;
        this.carteiraRepo = carteiraRepo;
        this.moedaRepo = moedaRepo;
    }

    //conversaoes
    private TransacaoDTO toDTO(Transacao t) {
        return new TransacaoDTO(
                t.getId(),
                t.getCarteiraId(),
                t.getMoedaId(),
                t.getQuantidade(),
                t.getTipo(),
                t.getData()
        );
    }

    private Transacao toEntity(TransacaoDTO dto) {
        Transacao t = new Transacao();
        t.setId(dto.id());
        t.setCarteiraId(dto.carteiraId());
        t.setMoedaId(dto.moedaId());
        t.setQuantidade(dto.quantidade());
        t.setTipo(dto.tipo());
        t.setData(dto.data());
        return t;
    }

    //métodos

    public TransacaoDTO criar(TransacaoDTO dto) {
        // validar carteira
        carteiraRepo.findById(dto.carteiraId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("ERR_CARTEIRA_NOT_FOUND", "Carteira não encontrada")
                );

        // validar moeda
        moedaRepo.findById(dto.moedaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("ERR_MOEDA_NOT_FOUND", "Moeda não encontrada")
                );

        Transacao entidade = toEntity(dto);
        Transacao salva = repo.save(entidade);

        return toDTO(salva);
    }

    public List<TransacaoDTO> listarPorCarteira(Long carteiraId) {
        return repo.findByCarteiraId(carteiraId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}
