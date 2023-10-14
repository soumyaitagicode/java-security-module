package com.auth;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTDecoderExample {

    public static void main(String[] args) throws Exception {
        String jwt = "7f170dbbf9f04630b6ca4fbc20ba8190"; // Replace with your JWT

        String publicKeyPEM = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj2bag8HJdKuXG0cixxTmKEa87//Fu3O3/VXHNJqj53iLPi9r/ajOxA69H8jaetr+F4ITNExIwFHw9l/MBbccTtgIaY8TMBnkK8HFvEZpBa48k6Rm7Mr5fRwzyeYve1cfd1OdPqR8h8MHp8mxSEt0LdUFgZAkToHDQ8wyRnGMoZC8rPxpUVjoJjaEPBC3B8OOvzlQv4iRE5uxFOGsVgwXMhi0r7H8Sa4c1JWYoAPM9B4RTuqbrWuSQjWQEv76XUgFCd22PvwrlON6N9M809jqB1ut0I14GtdKj1FQ4+2t5ZnjF9YY5zl1qJz82MPH7hmdzlA7snqCTSBQMNMoq2pHuwIDAQAB" +
                "-----END PUBLIC KEY-----";

        String privateKeyPEM = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPZtqDwcl0q5cbRyLHFOYoRrzv/8W7c7f9Vcc0mqPneIs+L2v9qM7EDr0fyNp62v4XghM0TEjAUfD2X8wFtxxO2AhpjxMwGeQrwcW8RmkFrjyTpGbsyvl9HDPJ5i97Vx93U50+pHyHwwenybFIS3Qt1QWBkCROgcNDzDJGcYyhkLys/GlRWOgmNoQ8ELcHw46/OVC/iJETm7EU4axWDBcyGLSvsfxJrhzUlZigA8z0HhFO6puta5JCNZAS/vpdSAUJ3bY+/CuU43o30zzT2OoHW63QjXga10qPUVDj7a3lmeMX1hjnOXWonPzYw8fuGZ3OUDuyeoJNIFAw0yirake7AgMBAAECggEAKbdZEAU6zrbwEV5jTpjUz5isdADrC7oBJzBh7/qgfcDCeQegmb0W0VAcUnfCnwS/8CTF42vhqhjsSS/bETqQgJs170dQ5KhTiSsok7nD6YQjWSv1SSNO6sdehXhyDaZtn+/yCXweQfxJaNUyvn48wOx8WpGNDk2PeVSYt4dmysT2NJKZUY8dU3kLc2ZB5aGNXItAhNCXTFIEsYeGTdTalgyrgmyOJcmz+31XjbLL9Goiju8Eg0Z09BcFjZHXMa7Z6iymgziq8UyczYFBtIz3QhopBieuWvD2NL2Y67lXliXXG0wBGr6TSL3MmucYqYf0lLrB0b7MkTPD0Ur6Z2CFeQKBgQD1/bR3A5Uhg5ZJx9VrHYtjaAV1zr+NG1HWF0Vmux6dUBJ3Hfhtmsn1vr739vn8UejoH47TSoy7zvw9cqeD9WBtqVNVyTqFCZG3eadCTljx4O0ZvbyCFpz1rTP0+wcLVqbLKqjpHJITYQQdXGN4brHmwlF36x7QXqkomFE2g8x3JQKBgQCVPI6XYeMaubCVmHoe+UeA/rSZgZocYflbHGJHmeQ+4fecNOy/6wk+Qk6F+pSRjBdI1VFX6VYZkWxaXb+H3kvm6MjHnRjqUIMGl3jj0VgeTCUWLyOrSGICMv4eAOf8rcmSSqwkAWO/ejf5RWgyzr3MfsVhcMtsUnGplsc11tl9XwKBgQCP3d6C0JSSsjveUMJP0EqqS7cNzGoZI8Y7+QY86+kexirWga++oo3FbNaRfV99I4ac44ALsQkX+hlp8rloIsVPvX0gqfzQu+T+MmcRD3NTm2Hv8Rjj2EfaI39Eot/e0MHiBsQc+fmw3cejSFpgoBTpyfINuXQfF43FUTgGMxUNBQKBgQCANsvG8Xl9L4WvsJVS4RX2lF2y+K7frajfBy7El0mKYPcrxCiW5iZF0A/IebWEqlgi4eqF3dl0FAWEScDQyUJquxosrSKHxO4dDqv1BplvRjoiqhM6B/D0NL0wJ9UnuGNShUcJlAwPqMBIL8JKzGYyN8P6aD7M7LPsYLb74X9vfwKBgEOVVdJ7ADHkc1bEfWl232roGAhxGeNm864seeMB5Hmipv4KdxIBIrwrSUsF+biUZdWkk9XN4WrUgiazT1mi9sqn23p8spZtlEtvevxI6fr2SpHtxH67UWAPHVVEWQ/6185Z/I+UqO7N+X6Hm1IxJRXsonTbDLuhyEt2I1E2LwXu" +
                "-----END PRIVATE KEY-----";

        RSAPublicKey publicKey = getPublicKeyFromPEM(publicKeyPEM);
        RSAPrivateKey privateKey = getPrivateKeyFromPEM(privateKeyPEM);

        Claims claims = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(jwt)
                .getBody();

        System.out.println("Decoded JWT Subject: " + claims.getSubject());
        System.out.println("Decoded JWT Expiration: " + claims.getExpiration());
    }

    private static RSAPublicKey getPublicKeyFromPEM(String publicKeyPEM) throws Exception {
        publicKeyPEM = publicKeyPEM
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    private static RSAPrivateKey getPrivateKeyFromPEM(String privateKeyPEM) throws Exception {
        privateKeyPEM = privateKeyPEM
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}