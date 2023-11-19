package com.example.UserService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
      security.authorizeHttpRequests()
              .anyRequest()
              .authenticated()
              .and()
              .oauth2ResourceServer()
              .jwt();
      return security.build();

   }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(withDefaults());
//
//        http.oauth2ResourceServer()
//                .jwt(jwt -> jwt
//                        .jwtAuthenticationConverter(jwtAuthenticationConverter()));
//
//        return http.build();
//    }
//
//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthoritiesConverter());
//        return converter;
//    }
}
