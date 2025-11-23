package com.cryptotracker.service;

import com.cryptotracker.exception.ResourceNotFoundException;
import com.cryptotracker.model.CarteiraMoeda;
import com.cryptotracker.repository.CarteiraMoedaRepository;
import com.cryptotracker.repository.CarteiraRepository;
import com.cryptotracker.repository.MoedaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarteiraMoedaServiceTest {

    private CarteiraMoedaRepository repo;
    private CarteiraRepository carteiraRepo;
    private MoedaRepository moedaRepo;
    private CarteiraMoedaService service;

    @BeforeEach
    void setup() {
        repo = mock(CarteiraMoedaRepository.class);
        carteiraRepo = mock(CarteiraRepository.class);
        moedaRepo = mock(MoedaRepository.class);
        service = new CarteiraMoedaService(repo, carteiraRepo, moedaRepo);
    }

    // TESTE 1 — adicionar novo registro
    // -------------------------------------------------------
    @Test
    void deveAdicionarNovaMoedaNaCarteira() {
        Long carteiraId = 1L;
        Long moedaId = 10L;

        when(carteiraRepo.findById(carteiraId)).thenReturn(Optional.of(new Object()));
        when(moedaRepo.findById(moedaId)).thenReturn(Optional.of(new Object()));
        when(repo.findByCarteiraIdAndMoedaId(carteiraId, moedaId)).thenReturn(Collections.emptyList());

        CarteiraMoeda esperado = new CarteiraMoeda(carteiraId, moedaId, 5.0);
        when(repo.save(any(CarteiraMoeda.class))).thenReturn(esperado);

        CarteiraMoeda result = service.adicionar(carteiraId, moedaId, 5.0);

        assertEquals(5.0, result.getQuantidade());
        verify(repo, times(1)).save(any(CarteiraMoeda.class));
    }

    // TESTE 2 — somar quantidade quando já existe
    // -------------------------------------------------------
    @Test
    void deveSomarQuantidadeQuandoRegistroJaExiste() {
        Long carteiraId = 1L;
        Long moedaId = 10L;

        when(carteiraRepo.findById(carteiraId)).thenReturn(Optional.of(new Object()));
        when(moedaRepo.findById(moedaId)).thenReturn(Optional.of(new Object()));

        CarteiraMoeda existente = new CarteiraMoeda(carteiraId, moedaId, 3.0);

        when(repo.findByCarteiraIdAndMoedaId(carteiraId, moedaId))
                .thenReturn(List.of(existente));

        existente.setQuantidade(8.0);
        when(repo.save(existente)).thenReturn(existente);

        CarteiraMoeda result = service.adicionar(carteiraId, moedaId, 5.0);

        assertEquals(8.0, result.getQuantidade());
        verify(repo, times(1)).save(existente);
    }

    // TESTE 3 — alternar favorito
    // -------------------------------------------------------
    @Test
    void deveAlternarFavorito() {
        Long carteiraId = 1L;
        Long moedaId = 10L;

        CarteiraMoeda cm = new CarteiraMoeda(carteiraId, moedaId, 1.0);
        cm.setFavorito(false);

        when(repo.findByCarteiraIdAndMoedaId(carteiraId, moedaId))
                .thenReturn(List.of(cm));

        cm.setFavorito(true);
        when(repo.save(cm)).thenReturn(cm);

        CarteiraMoeda result = service.alternarFavorito(carteiraId, moedaId);

        assertTrue(result.isFavorito());
        verify(repo).save(cm);
    }

    // TESTE 4 — alternar favorito com registro inexistente
    // -------------------------------------------------------
    @Test
    void deveLancarErroQuandoAlternarFavoritoEmRegistroInexistente() {
        when(repo.findByCarteiraIdAndMoedaId(1L, 10L))
                .thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () ->
                service.alternarFavorito(1L, 10L)
        );
    }

    // TESTE 5 — listar por carteira
    // -------------------------------------------------------
    @Test
    void deveListarPorCarteira() {
        when(repo.findByCarteiraId(1L)).thenReturn(List.of(new CarteiraMoeda(1L, 9L, 2.0)));

        var lista = service.listarPorCarteira(1L);

        assertEquals(1, lista.size());
        verify(repo).findByCarteiraId(1L);
    }

    // TESTE 6 — remover por id
    // -------------------------------------------------------
    @Test
    void deveRemoverRegistro() {
        service.remover(55L);
        verify(repo).deleteById(55L);
    }
}
