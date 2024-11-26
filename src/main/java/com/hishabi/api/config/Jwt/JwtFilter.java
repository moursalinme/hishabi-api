package com.hishabi.api.config.Jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader("Authorization");

        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);

            try {
                if (jwtService.isValidToken(jwt)) {

                    Claims claims = jwtService.extractAllClaims(jwt);

                    Authentication authToken = new UsernamePasswordAuthenticationToken(
                            String.valueOf(claims.getSubject()), null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(claims.get("roles"))));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info(SecurityContextHolder.getContext().toString());
                }
            } catch (Exception ex) {
                logger.debug(ex.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

}
