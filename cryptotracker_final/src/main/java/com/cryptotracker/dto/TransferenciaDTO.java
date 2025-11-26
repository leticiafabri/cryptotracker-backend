package com.cryptotracker.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferenciaDTO {
    private Long id;
    private Long carteiraOrigem;
    private Long carteiraDestino;
    private Long moedaId;
    private BigDecimal quantidade;
}
