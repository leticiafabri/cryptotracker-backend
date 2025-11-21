package com.cryptotracker.dto;

import java.util.Set;
import com.cryptotracker.model.Role;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        Set<Role> roles
) {}
