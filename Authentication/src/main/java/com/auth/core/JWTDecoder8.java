package com.auth.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;
import java.util.Date;

public class JWTDecoder8 {

    public static void main(String[] args) {
        // Generate a sample JWT with RS256
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

        String jwt = Jwts.builder()
                .setSubject("user123")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();

        // Print the generated JWT
        System.out.println("Generated JWT: " + jwt);

        // Decode and validate the JWT using the public key
        KeyPair publicKeyPair = keyPair; // For demonstration, we use the same key pair as public and private. In practice, you would use a separate public key.

        Claims claims = Jwts.parser()
                .setSigningKey(publicKeyPair.getPublic())
                .parseClaimsJws(jwt)
                .getBody();

        System.out.println("Decoded JWT Subject: " + claims.getSubject());
        System.out.println("Decoded JWT Expiration: " + claims.getExpiration());
    }
}