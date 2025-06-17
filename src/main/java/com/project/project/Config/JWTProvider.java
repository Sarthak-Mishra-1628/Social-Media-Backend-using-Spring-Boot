package com.project.project.Config;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

public class JWTProvider {
    private static SecretKey key = Keys.hmacShaKeyFor(Constants.Secret_Key.getBytes());

    public static String generateToken(Authentication auth){

        String jwt = Jwts.builder().setIssuer("Sarthak").
                     setIssuedAt(new Date()).setExpiration(
                     new Date(new Date().getTime()+86400000))
                     .claim("email" , auth.getName())
                     .signWith(key).compact();
        
        return jwt;
    }
    

    public static String getEmailFromJWTToken(String jwt){
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).
                        build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }
}
