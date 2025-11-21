package com.cryptotracker.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.cryptotracker.repository.UsuarioRepository;
import com.cryptotracker.model.Usuario;
import java.io.IOException;
import java.util.stream.Collectors;
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    public JwtFilter(JwtUtil jwtUtil, UsuarioRepository usuarioRepository){ this.jwtUtil=jwtUtil; this.usuarioRepository=usuarioRepository; }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            if(jwtUtil.validateToken(token)){
                String email = jwtUtil.getUsernameFromToken(token);
                var opt = usuarioRepository.findByEmail(email);
                if(opt.isPresent()){
                    Usuario u = opt.get();
                    var authorities = u.getRoles().stream().map(r->new SimpleGrantedAuthority(r.name())).collect(Collectors.toList());
                    var auth = new UsernamePasswordAuthenticationToken(u.getEmail(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
