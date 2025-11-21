package com.cryptotracker.config;
import com.cryptotracker.model.Role;
import com.cryptotracker.model.Usuario;
import com.cryptotracker.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Set;
@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder){
        return args -> {
            if(!usuarioRepository.existsByEmail("admin@cryptotracker.com")){
                Usuario admin = new Usuario("Admin", "admin@cryptotracker.com", encoder.encode("admin123"), Set.of(Role.ROLE_ADMIN));
                usuarioRepository.save(admin);
            }
        };
    }
}
