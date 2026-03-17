package com.example.shopping.security;

import com.example.shopping.security.JwtUtil;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;              // your JWT helper (extractUsername, isTokenValid)
    private final UserDetailsService userSvc;   // your UserDetailsService

    public JwtAuthFilter(JwtUtil jwtUtil,
                         UserDetailsService userSvc) {
        this.jwtUtil = jwtUtil;
        this.userSvc = userSvc;
    }


    @Override
protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getServletPath();
    String method = request.getMethod();
    // 1) Skip preflight
    if ("OPTIONS".equalsIgnoreCase(method)) {
        return true;
    }
    // 2) Skip furniture endpoints
    if (path.startsWith("/api/v1/furniture") ||
        path.startsWith("/api/v1/users/register") ||
        path.startsWith("/api/v1/users/login")) {
        return true;
    }
    // 3) Otherwise run the JWT logic
    return false;
}

    @Override
protected void doFilterInternal(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull FilterChain filterChain)
                                throws ServletException, IOException {
    // 1) Grab the Authorization header
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        // no token or wrong scheme → skip straight to the next filter
        filterChain.doFilter(request, response);
        return;
    }

    // 2) Strip off "Bearer "
    final String jwtToken = authHeader.substring(7);

    // 3) Try to extract username (may throw on invalid/expired token)
    String username;
    try {
        username = jwtUtil.extractUsername(jwtToken);
    } catch (JwtException | IllegalArgumentException ex) {
        // invalid token → continue without setting SecurityContext
        filterChain.doFilter(request, response);
        return;
    }

    // 4) If we got a username and no one’s authenticated yet
    if (username != null
        && SecurityContextHolder.getContext().getAuthentication() == null) {

        // load the user and validate the token
        UserDetails userDetails = userSvc.loadUserByUsername(username);
        if (jwtUtil.isTokenValid(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
            authToken.setDetails(
                new WebAuthenticationDetailsSource()
                    .buildDetails(request)
            );
            SecurityContextHolder
                .getContext()
                .setAuthentication(authToken);
        }
    }

    // 5) Proceed down the chain in all cases
    filterChain.doFilter(request, response);
}

}
