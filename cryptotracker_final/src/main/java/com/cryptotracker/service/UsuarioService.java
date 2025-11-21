package com.cryptotracker.service;
import com.cryptotracker.model.Role;
import com.cryptotracker.model.Usuario;
import com.cryptotracker.repository.UsuarioRepository;
import com.cryptotracker.exception.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    private final BCryptPasswordEncoder encoder;
    public UsuarioService(UsuarioRepository repo, BCryptPasswordEncoder encoder){this.repo=repo;this.encoder=encoder;}
    public Usuario criar(Usuario u){
        if(repo.existsByEmail(u.getEmail())) throw new RuntimeException("ERR_EMAIL_EXISTS");
        u.setSenha(encoder.encode(u.getSenha()));
        if(u.getRoles()==null) u.setRoles(Set.of(Role.ROLE_USER));
        return repo.save(u);
    }
    public Usuario buscar(Long id){ return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("ERR_USER_NOT_FOUND","Usuário não encontrado")); }
}
