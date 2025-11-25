package com.cryptotracker.dto;

public record CarteiraMoedaDTO(
        Long carteiraId,
        Long moedaId,
        double quantidade
) {}
