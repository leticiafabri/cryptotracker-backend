package com.cryptotracker.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.security.Key;
@Component
public class JwtUtil {
    @Value("${app.jwtSecret}") private String jwtSecret;
    @Value("${app.jwtExpirationMs}") private int jwtExpirationMs;
    public String generateToken(String username){
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+jwtExpirationMs)).signWith(key, SignatureAlgorithm.HS256).compact();
    }
    public String getUsernameFromToken(String token){
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try{ Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes()); Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); return true; } catch(Exception e){ return false; }
    }
}
