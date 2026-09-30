package lk.ijse.cmjd._115.movie_ticket_booking.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private final int jwtExpirationMs = 86400000; // පැය 24ක්

    private Key key() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Login වන විට Token එකක් හදාදීම
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Token එකෙන් Username එක ලබාගැනීම
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // Token එක valid ද යන්න පරීක්ෂා කිරීම
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}