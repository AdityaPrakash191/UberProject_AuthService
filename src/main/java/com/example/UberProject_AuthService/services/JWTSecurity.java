package com.example.UberProject_AuthService.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurity implements CommandLineRunner {

    @Value("${jwt.secret}")
    private  String secret;

    @Value("${jwt.expiry}")
    private int expiry;

    private String createToken(Map<String,Object> payload , String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(username)
                .signWith(key)
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

}
