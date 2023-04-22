package com.sebastiansiarczynski.SpringBootTest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class GlobalWebConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
      final @Value("${jwt.secret}") String jwtCarSecret,
      final @Value("${jwt.issuer}") String jwtCarIssuer,
      final @Value("${jwt.secret}") String jwtUserSecret,
      final @Value("${jwt.issuer}") String jwtUserIssuer) throws Exception {
    httpSecurity.csrf().disable();

    httpSecurity.authorizeHttpRequests()
        .requestMatchers("/car/**")
        .authenticated()
        .and()
        .addFilterBefore(this.carJwtFilter(jwtCarSecret, jwtCarIssuer),
            UsernamePasswordAuthenticationFilter.class);

    httpSecurity
        .authorizeHttpRequests()
        .requestMatchers("/user/**")
        .authenticated()
        .and()
        .addFilterBefore(userJwtFilter(jwtUserSecret, jwtUserIssuer),
            UsernamePasswordAuthenticationFilter.class);

    httpSecurity.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return httpSecurity.build();
  }


  @Bean
  public CarJwtFilter carJwtFilter(final @Value("${jwt.secret}") String jwtCarSecret,
      final @Value("${jwt.issuer}") String jwtCarIssuer) {
    return new CarJwtFilter(jwtCarSecret, jwtCarIssuer);
  }

  @Bean
  public UserJwtFilter userJwtFilter(final @Value("${jwt.secret}") String jwtUserSecret,
      final @Value("${jwt.issuer}") String jwtUserIssuer) {
    return new UserJwtFilter(jwtUserSecret, jwtUserIssuer);
  }
}
