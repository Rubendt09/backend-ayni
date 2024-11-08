package com.ayni.backhacka.infraestructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {
    private final String jwtSecret = "my_secret_key";
    private final long jwtExpirationInMs = 86400000;

    public String generateToken(String email) {
        Date now = new Date();
        Date expirityDate  = new Date(now.getTime() + jwtExpirationInMs);

        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now).withExpiresAt(expirityDate)
                .sign(Algorithm.HMAC256(String.valueOf(jwtSecret)));
    }

    public DecodedJWT decodeToken(String token){
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .build().verify(token);
    }
}
