package com.music.app.infrastructure.security;

import com.music.app.infrastructure.wrappers.TokenWrapper;
import com.music.app.domain.dtos.UserAccountDto;
import com.music.app.domain.ports.TokenGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService implements TokenGenerator {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationTime;

    @Override
    public TokenWrapper generateToken(UserAccountDto authenticatedUserAccount) {
        String jwtToken = Jwts
                .builder()
                .subject(authenticatedUserAccount.email())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
                .signWith(getSignInKey())
                .compact();
        return new TokenWrapper(jwtToken, jwtExpirationTime);

    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean isTokenValid(String token, String userEmail) {
        final String userEmailInToken = extractUserAccountEmail(token);
        return (userEmailInToken.equals(userEmail)) && !isExpired(token);
    }

    public String extractUserAccountEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
