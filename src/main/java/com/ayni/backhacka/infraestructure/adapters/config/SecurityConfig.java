package com.ayni.backhacka.infraestructure.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors()  // Habilitar CORS
                .and()
                .csrf().disable()  // For not use Cookies in the Api
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/user/login", "/api/user/register").permitAll()  // Access to authenticate
                        .anyRequest().authenticated())  // Require authenticate for eny more request
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Not state
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        //http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();  // Rev JwtRequestFilter was configured how Bean if is necessary
    }
}
