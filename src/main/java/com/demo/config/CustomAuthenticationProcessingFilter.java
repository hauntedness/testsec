package com.demo.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    public CustomAuthenticationProcessingFilter(CustomAuthenticationManager customAuthenticationManager) {
        this.setAuthenticationManager(customAuthenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        // return request.getHeader(HttpHeaders.AUTHORIZATION);
        return request.getParameter("token");
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

}
