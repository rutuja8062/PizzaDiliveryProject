package com.example.pizzaDelivery.Service.Implementation;

import com.example.pizzaDelivery.Entity.User;
import com.example.pizzaDelivery.Service.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtGenerator  implements Token {
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 1000;
    public static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //Method to generate JWT
    @Override
    public String generateToken(User user) {



        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);
        user.setPassword("");

        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))  //1 HOUR
                .signWith(SIGNING_KEY)
                .compact();

        return jwtToken;
    }

    /*private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }*/

}
