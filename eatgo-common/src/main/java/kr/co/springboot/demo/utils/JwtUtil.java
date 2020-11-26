package kr.co.springboot.demo.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;


public class JwtUtil {

    private Key key;

    public JwtUtil(String secret){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String name, Long restaurantId) {


        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name);
        if(restaurantId != null){
            builder = builder.claim("restaurantId",restaurantId);
        }
        return builder
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {

        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
