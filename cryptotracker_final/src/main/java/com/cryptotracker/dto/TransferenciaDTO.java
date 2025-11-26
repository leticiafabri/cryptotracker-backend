package com.cryptotracker.dto;

public record TransferenciaDTO(
        Long carteiraOrigemId,
        Long carteiraDestinoId,
        Long moedaId,
        Double quantidade
) {}
