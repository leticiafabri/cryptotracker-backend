package com.cryptotracker.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="transacoes")
public class Transacao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long carteiraId;
    private Long moedaId;
    private double quantidade;
    private double valorUnitario;
    private String tipo; // COMPRA, VENDA
    private LocalDateTime data = LocalDateTime.now();
    public Transacao(){} public Transacao(Long carteiraId,Long moedaId,double quantidade,double valorUnitario,String tipo){this.carteiraId=carteiraId;this.moedaId=moedaId;this.quantidade=quantidade;this.valorUnitario=valorUnitario;this.tipo=tipo;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getCarteiraId(){return carteiraId;} public void setCarteiraId(Long carteiraId){this.carteiraId=carteiraId;}
    public Long getMoedaId(){return moedaId;} public void setMoedaId(Long moedaId){this.moedaId=moedaId;}
    public double getQuantidade(){return quantidade;} public void setQuantidade(double quantidade){this.quantidade=quantidade;}
    public double getValorUnitario(){return valorUnitario;} public void setValorUnitario(double valorUnitario){this.valorUnitario=valorUnitario;}
    public String getTipo(){return tipo;} public void setTipo(String tipo){this.tipo=tipo;}
    public LocalDateTime getData(){return data;} public void setData(LocalDateTime data){this.data=data;}
}
