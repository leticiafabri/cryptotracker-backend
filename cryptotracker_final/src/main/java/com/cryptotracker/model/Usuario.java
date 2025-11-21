package com.cryptotracker.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String nome;
    @Email @Column(unique=true) private String email;
    @NotBlank private String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    public Usuario(){}
    public Usuario(String nome,String email,String senha,Set<Role> roles){this.nome=nome;this.email=email;this.senha=senha;this.roles=roles;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getNome(){return nome;} public void setNome(String nome){this.nome=nome;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getSenha(){return senha;} public void setSenha(String senha){this.senha=senha;}
    public Set<Role> getRoles(){return roles;} public void setRoles(Set<Role> roles){this.roles=roles;}
}
