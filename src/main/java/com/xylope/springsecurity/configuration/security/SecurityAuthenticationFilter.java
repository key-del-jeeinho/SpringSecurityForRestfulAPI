package com.xylope.springsecurity.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class SecurityAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserDetails authentication = customUserDetailsService.loadUserByUsername("test");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authentication.getUsername(),
                null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);

    }
}
