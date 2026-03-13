package com.eazybytes.security.securitydemo2.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class JWTTokenGenerationFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //read authentication info
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            //generate signuature with secret from secrets vault
            //Environment environment = (Environment) authentication.getPrincipal();
            Environment environment = getEnvironment();
            String secretKeyStr =environment.getProperty("jwt.token.secret-key");

            UUID uuid = UUID.randomUUID();

            //once jwt is generated
            String jwt = Jwts.builder()
                    .issuer("PardoFintech")
                    .subject("JWTToken")
                    .claim("username", authentication.getName())
                    .claim("authorities", authentication.getAuthorities())
                    .claim("transaction_id", uuid.toString())
                    .issuedAt(new Date())
                    .signWith(Keys.hmacShaKeyFor(secretKeyStr.getBytes(StandardCharsets.UTF_8)))
                    .compact();
            HttpServletResponse servletResponse = (HttpServletResponse) response;

            servletResponse.setHeader("Authorization", "Bearer " + jwt);

        }

        super.doFilter(request, response, filterChain);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        //return true when authenticated
        //false if processing login request

        return !request.getServletPath().equals("/login");
    }
}
