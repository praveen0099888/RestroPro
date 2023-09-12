package com.example.BillMicroservice.BillMicroservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtUtil {
    private  String secert ="Cohort@22";


    public String extractUsername(String token){
        return extracClaims(token, Claims::getSubject);

    }
    public Date extractExpiration(String token){
        return  extracClaims(token, Claims::getExpiration);
    }

    public <T> T extracClaims(String token , Function<Claims,T> claimsresolver){
        final Claims claims = extractAllClamis(token);
        return claimsresolver.apply(claims);
    }
    public String generateToken(String username,String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);
        return  Createtoken(claims,username);
    }
    private String Createtoken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,secert)
                .compact();
    }

    public Claims extractAllClamis(String token){
        return Jwts.parser().setSigningKey(secert).parseClaimsJws(token).getBody();
    }

}
