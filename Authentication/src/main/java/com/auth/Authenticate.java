package com.auth;

import com.auth.core.JWTDecode;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.InvalidParameterException;

public class Authenticate {
    public static void main(String[] args) {
        final JWTDecode validator = new JWTDecode();
        try {
            DecodedJWT token = validator.validate("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiaXNzIjoiY29tcGFueSIsIm5hbWUiOiJzb3VteWEiLCJleHAiOiIxNTE2MjM5MDIyIiwiaWF0IjoxNTE2MjM5MDIyfQ.eVkrrCgimqZmV1uksbs57Vz1eBtS0Xtp8eHo0O0Yjo_WxkNSR77J-8zRHXGSFEW7eZDAXvfCRuNZcb_UJ0lrlII-JCZNtzG-bLUydxzelN3F8k1WJqL8HH3bLAtMnyOCAN_AKvAcVB0doIB_g-ZMoPRHFoQuHMjADuwFEPDDtI9SePhWtfgThU4_drl-5UWEhZ2KwwRpXgbL15D0b8EHeSFpAdplQmX66uGCzwWDKl35f0iADTqdjUQKoWLlrsvLZIofRvUtAU1SGAo9Xlw7SYcZtB0j_qfsds5g7w_w8DEHzVqnezF-k77K_Yp1kCU71JusDSMqqVje5yrjBbPkxw");
            System.out.println("Jwt is valid");
        } catch (InvalidParameterException e) {
            System.out.println("Jwt is invalid");
            e.printStackTrace();
        }

    }
}

