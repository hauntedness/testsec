package com.demo.common;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

@SpringBootTest
class JwtTest {

    private final String key = "demo";
    private final Date iat = new Date();
    private final String jti = "1234";
    private final String sub = "sub";

    private String buildToken() {
        JwtBuilder jwtBuilder = Jwts.builder().setId(jti).setSubject(sub).setIssuedAt(iat)
                .signWith(SignatureAlgorithm.HS256, key);

        String token = jwtBuilder.compact();
        System.out.println(token);
        String[] jwt = token.split("\\.");

        System.out.println("header: " + Base64Codec.BASE64.decodeToString(jwt[0]));
        System.out.println("payload: " + Base64Codec.BASE64.decodeToString(jwt[1]));
        System.out.println("signature: " + Base64Codec.BASE64.decodeToString(jwt[2]));
        return token;
    }

    @Test
    void testJwtParse() {

        String token = buildToken();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        Assertions.assertEquals(iat.toString(), claims.getIssuedAt().toString());
        Assertions.assertEquals(jti, claims.getId());
        Assertions.assertEquals(sub, claims.getSubject());
    }
}
