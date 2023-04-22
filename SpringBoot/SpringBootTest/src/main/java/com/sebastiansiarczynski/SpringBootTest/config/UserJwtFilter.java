package com.sebastiansiarczynski.SpringBootTest.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sebastiansiarczynski.SpringBootTest.config.role.SCOPE_ROLE;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class UserJwtFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER_NAME = "X-User-Authorization";
  private static final String CLAIM_ROLE_NAME = "USER-SCOPE-ROLE";

  private final String jwtSecret;
  private final String jwtIssuer;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    System.out.println("STARTED USER JWT FILTER");

    final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);

    if (authorizationHeader == null || authorizationHeader.isBlank()) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());

      return;
    }

    final String token = authorizationHeader.split("Bearer ")[1];
    System.out.println("TOKEN: " + token);
    Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
    JWTVerifier verifier = JWT.require(algorithm)
        .withIssuer(jwtIssuer)
        .build();

    DecodedJWT decodedJWT = verifier.verify(token);

    Claim role = decodedJWT.getClaim(CLAIM_ROLE_NAME);
    SCOPE_ROLE scopeRole = role.as(SCOPE_ROLE.class);

    if (scopeRole != SCOPE_ROLE.User) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());

      return;
    }

    Authentication authentication = new UsernamePasswordAuthenticationToken("noname", scopeRole,
        new ArrayList<>());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);

    System.out.println("ENDED USER JWT FILTER");
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    System.out.println("USER JWT FILTER URI: " + request.getRequestURI());
    return !request.getRequestURI().startsWith("/user");
  }
}
