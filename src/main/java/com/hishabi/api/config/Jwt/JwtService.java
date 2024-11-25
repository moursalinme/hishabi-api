package com.hishabi.api.config.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.hishabi.api.dto.response.UserResponseDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final static String JwtSECRET = "324fa68ec33e3a0cd9211e42e56c2b7260cea7bbbcae91091d07b2a35c9378a3";
    private final static Long JwtEXP = 1000 * 60 * 60L; // 1hr

    public String generateToken(UserResponseDto user) {
        return Jwts.builder()
                .setIssuer("hishabi_api")
                .setSubject(user.getEmail())
                .claim("roles", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtEXP))
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(JwtSECRET.getBytes());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isValidToken(String token) {
        return extractClaim(token, Claims::getExpiration).after(new Date());
    }

    public String getSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
