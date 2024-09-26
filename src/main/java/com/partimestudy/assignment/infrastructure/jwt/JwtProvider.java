package com.partimestudy.assignment.infrastructure.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.partimestudy.assignment.domain.exception.ErrorCode;
import com.partimestudy.assignment.domain.exception.UnAuthorizedException;
import com.partimestudy.assignment.domain.token.TokenProvider;
import com.partimestudy.assignment.infrastructure.common.properties.JwtProperties;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider implements TokenProvider {
    private final SecretKey secretKey;
    private final long accessTokenExpirationTime;

    public JwtProvider(JwtProperties properties) {
        this.secretKey = Keys.hmacShaKeyFor(properties.secretKey().getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationTime = properties.accessTokenExpirationTime();
    }

    @Override
    public String createAccessToken(String userToken) {
        Date now = new Date();
        Date accessTokenExpiration = new Date(now.getTime() + accessTokenExpirationTime);

        return Jwts.builder()
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .setIssuedAt(now)
            .setExpiration(accessTokenExpiration)
            .addClaims(Map.of("userToken", userToken))
            .compact();
    }

    @Override
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJwt(token);
        } catch (ExpiredJwtException e) {
            throw new UnAuthorizedException(ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new UnAuthorizedException(ErrorCode.INVALID_TOKEN);
        }
    }
}
