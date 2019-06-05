package br.com.alura.forum.security.controller.dto.input;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {
	@Value("${alura.forum.jwt.secret}")
	private String secret;
	@Value("${alura.forum.jwt.expiration}")
	private long expirationInMillis;

	public String generateToken(Authentication authenticated) {
		User user = (User) authenticated.getPrincipal();
		
		final Date now = new Date();
        final Date expiration = new Date(now.getTime() + 
                this.expirationInMillis);
        
        return Jwts.builder()
            .setIssuer("Alura Fórum API")
            .setSubject(Long.toString(user.getId()))
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, this.secret)
            .compact();

	}
}
