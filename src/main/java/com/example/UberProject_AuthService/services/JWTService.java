package com.example.UberProject_AuthService.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService implements CommandLineRunner {

    @Value("${jwt.secret}")
    private  String secret;

    @Value("${jwt.expiry}")
    private int expiry;

    private String createToken(Map<String,Object> payload , String email){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);


        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(email)
                .signWith(getSignKey())
                .compact();

    }
    @Override
    public void run(String... args) throws Exception{
        Map<String,Object> m = new HashMap<>();
        m.put("email","abc@123@gmail.com");
        m.put("phoneNumber","12345678");
        String token = createToken(m,"sanket");
        System.out.println("The generated token is " + token);

    }

    private Claims extractAllPayload(String token) {
        return Jwts
                .parser()
                .verifyWith((getSignKey()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllPayload(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private String extractEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }

    private Boolean validateToken(String email, String token){
        String userEmailFetchedFromToken = extractEmail(token);
        return (userEmailFetchedFromToken.equals(email) && !isTokenExpired(token));
    }
    private Object extractPayload(String token, String payloadKey){
        Claims claim = extractAllPayload(token);
        return  (Object)claim.get(payloadKey);
    }
}
