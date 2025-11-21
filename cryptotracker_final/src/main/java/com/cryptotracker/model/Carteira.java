package com.cryptotracker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name="carteiras")
public class Carteira {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String nome;
    private Long usuarioId;
    public Carteira(){} public Carteira(String nome,Long usuarioId){this.nome=nome;this.usuarioId=usuarioId;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;}
    public Long getUsuarioId(){return usuarioId;} public void setUsuarioId(Long usuarioId){this.usuarioId=usuarioId;}
}
