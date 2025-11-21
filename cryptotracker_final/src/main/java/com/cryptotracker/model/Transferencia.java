package com.cryptotracker.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="transferencias")
public class Transferencia {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long carteiraOrigem;
    private Long carteiraDestino;
    private Long moedaId;
    private double quantidade;
    private LocalDateTime data = LocalDateTime.now();
    public Transferencia(){} public Transferencia(Long carteiraOrigem,Long carteiraDestino,Long moedaId,double quantidade){this.carteiraOrigem=carteiraOrigem;this.carteiraDestino=carteiraDestino;this.moedaId=moedaId;this.quantidade=quantidade;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getCarteiraOrigem(){return carteiraOrigem;} public void setCarteiraOrigem(Long carteiraOrigem){this.carteiraOrigem=carteiraOrigem;}
    public Long getCarteiraDestino(){return carteiraDestino;} public void setCarteiraDestino(Long carteiraDestino){this.carteiraDestino=carteiraDestino;}
    public Long getMoedaId(){return moedaId;} public void setMoedaId(Long moedaId){this.moedaId=moedaId;}
    public double getQuantidade(){return quantidade;} public void setQuantidade(double quantidade){this.quantidade=quantidade;}
    public LocalDateTime getData(){return data;} public void setData(LocalDateTime data){this.data=data;}
}
