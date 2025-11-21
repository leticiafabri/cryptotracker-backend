package com.cryptotracker.controller;

import com.cryptotracker.dto.RegistroDTO;
import com.cryptotracker.dto.UsuarioResponseDTO;
import com.cryptotracker.model.Role;
import com.cryptotracker.model.Usuario;
import com.cryptotracker.repository.UsuarioRepository;
import com.cryptotracker.security.JwtUtil;
import com.cryptotracker.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder){
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegistroDTO dto){

        Usuario u = new Usuario();
        u.setNome(dto.nome());
        u.setEmail(dto.email());
        u.setSenha(dto.senha());
        u.setRoles(Set.of(Role.ROLE_USER));

        Usuario criado = usuarioService.criar(u);

        return ResponseEntity.ok(
                new UsuarioResponseDTO(
                        criado.getId(),
                        criado.getNome(),
                        criado.getEmail(),
                        criado.getRoles()
                )
        );
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registrarAdmin(@RequestBody RegistroDTO dto){

        Usuario u = new Usuario();
        u.setNome(dto.nome());
        u.setEmail(dto.email());
        u.setSenha(dto.senha());
        u.setRoles(Set.of(Role.ROLE_ADMIN));

        Usuario criado = usuarioService.criar(u);

        return ResponseEntity.ok(
                new UsuarioResponseDTO(
                        criado.getId(),
                        criado.getNome(),
                        criado.getEmail(),
                        criado.getRoles()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody java.util.Map<String,String> req){
        var opt = usuarioRepository.findByEmail(req.get("email"));
        if(opt.isEmpty())
            return ResponseEntity.badRequest().body("Credenciais inválidas");

        var u = opt.get();

        if(!encoder.matches(req.get("senha"), u.getSenha()))
            return ResponseEntity.badRequest().body("Credenciais inválidas");

        String token = jwtUtil.generateToken(u.getEmail());

        return ResponseEntity.ok(java.util.Map.of("token", token));
    }
}
