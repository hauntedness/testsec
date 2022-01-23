package com.demo.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class InterServiceAuthenticationProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    public InterServiceAuthenticationProcessingFilter(
            InterServiceAuthenticationManager interServiceAuthenticationManager) {
        setAuthenticationManager(interServiceAuthenticationManager);
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
