package com.example.todoserver.filter;

import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // extract username and password from requestrequest = {HeaderWriterFilter$HeaderWriterRequest@13548}
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // create an authentication object with the user's credentials
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        // authenticate the user with the authentication manager
        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {
        // create a JWT token
        List<String> userRoles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Algorithm algorithm = Algorithm.HMAC256("secret");

        String token = JWT.create()
                .withSubject(authentication.getName())
                .withClaim("roles" , userRoles)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
                .sign(algorithm);

        // add the token to the response header
        response.addHeader("Authorization", "Bearer " + token);
    }
}
