package com.cryptotracker.controller;

import com.cryptotracker.dto.RegistroDTO;
import com.cryptotracker.model.Role;
import com.cryptotracker.model.Usuario;
import com.cryptotracker.repository.UsuarioRepository;
import com.cryptotracker.security.JwtUtil;
import com.cryptotracker.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    // TESTE 1 — registrar usuário comum
    // -------------------------------------------------------------
    @Test
    void deveRegistrarUsuario() throws Exception {
        Usuario criado = new Usuario();
        criado.setId(1L);
        criado.setNome("Maria");
        criado.setEmail("maria@mail.com");
        criado.setRoles(Set.of(Role.ROLE_USER));

        when(usuarioService.criar(any(Usuario.class))).thenReturn(criado);

        String body = """
        {
          "nome": "Maria",
          "email": "maria@mail.com",
          "senha": "123"
        }
        """;

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Maria"))
                .andExpect(jsonPath("$.email").value("maria@mail.com"));
    }

    // TESTE 2 — registrar admin
    // -------------------------------------------------------------
    @Test
    void deveRegistrarAdmin() throws Exception {
        Usuario criado = new Usuario();
        criado.setId(2L);
        criado.setNome("AdminX");
        criado.setEmail("admin@mail.com");
        criado.setRoles(Set.of(Role.ROLE_ADMIN));

        when(usuarioService.criar(any(Usuario.class))).thenReturn(criado);

        String body = """
        {
          "nome": "AdminX",
          "email": "admin@mail.com",
          "senha": "123"
        }
        """;

        mockMvc.perform(post("/auth/register-admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.roles[0]").value("ROLE_ADMIN"));
    }

    // TESTE 3 — login bem-sucedido
    // -------------------------------------------------------------
    @Test
    void deveRealizarLogin() throws Exception {
        Usuario u = new Usuario();
        u.setEmail("teste@mail.com");
        u.setSenha("ENCODED");

        when(usuarioRepository.findByEmail("teste@mail.com")).thenReturn(Optional.of(u));
        when(encoder.matches("123", "ENCODED")).thenReturn(true);
        when(jwtUtil.generateToken("teste@mail.com")).thenReturn("TOKEN123");

        String body = """
        {
          "email": "teste@mail.com",
          "senha": "123"
        }
        """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("TOKEN123"));
    }

    // TESTE 4 — login com email inexistente
    // -------------------------------------------------------------
    @Test
    void deveRecusarLoginEmailInexistente() throws Exception {

        when(usuarioRepository.findByEmail("x@mail.com")).thenReturn(Optional.empty());

        String body = """
        {
          "email": "x@mail.com",
          "senha": "123"
        }
        """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Credenciais inválidas"));
    }

    // TESTE 5 — login com senha incorreta
    // -------------------------------------------------------------
    @Test
    void deveRecusarLoginSenhaIncorreta() throws Exception {
        Usuario u = new Usuario();
        u.setEmail("a@mail.com");
        u.setSenha("HASH");

        when(usuarioRepository.findByEmail("a@mail.com")).thenReturn(Optional.of(u));
        when(encoder.matches("123", "HASH")).thenReturn(false);

        String body = """
        {
          "email": "a@mail.com",
          "senha": "123"
        }
        """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Credenciais inválidas"));
    }

}
