package br.com.javalirica.security.jtw;

import br.com.javalirica.service.GerenciadorDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationMs}")
	private int jwtExpirationMs;

	public String generateTokenFromUserDetailsImpl(GerenciadorDetails gerenciadorDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

		return Jwts.builder()
				.setSubject(gerenciadorDetails.getUsername())
				.claim("gerenciadorRole", gerenciadorDetails.getRole())
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(getSigninKey(), SignatureAlgorithm.HS512)
				.compact();
	}


	public Key getSigninKey() {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		return key;
	}

	public String getUsernameToken(String token) {
		return Jwts.parser().setSigningKey(getSigninKey()).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
	public Long getUserIdFromToken(String token) {
		return Jwts.parser().setSigningKey(getSigninKey()).build()
				.parseClaimsJws(token).getBody().get("userId", Long.class);
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
			return true;
		}catch(MalformedJwtException e) {
			System.out.println("Token inválido " + e.getMessage());
		}catch(ExpiredJwtException e) {
			System.out.println("Token expirado " + e.getMessage());
		}catch(UnsupportedJwtException e) {
			System.out.println("Token não suportado " + e.getMessage());
		}catch(IllegalArgumentException e) {
			System.out.println("Token Argumento inválido " + e.getMessage());
		}

		return false;
	}
}
