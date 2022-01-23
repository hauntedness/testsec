package com.demo.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if ("1234".equals(authentication.getPrincipal())) {
            authentication.setAuthenticated(true);
            return authentication;
        } else {
            throw new AuthenticationException("do not have access") {
            };
        }
    }

}
