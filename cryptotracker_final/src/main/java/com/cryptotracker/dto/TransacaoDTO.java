package com.cryptotracker.dto;

public record TransacaoDTO(
        Long id,
        Long carteiraId,
        Long moedaId,
        Double quantidade,
        Double preco,
        String tipo //venda ou compra
) {}
