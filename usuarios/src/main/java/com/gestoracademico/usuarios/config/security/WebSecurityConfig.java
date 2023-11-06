package com.gestoracademico.usuarios.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gestoracademico.usuarios.config.security.jwt.JwtAuthFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChan(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception{
		http
		.cors(Customizer.withDefaults())
        .exceptionHandling(exception -> exception.accessDeniedPage("/Acceso_Denegado"))
        .csrf(AbstractHttpConfigurer::disable) // Equivalente en lambda  .csrf(csrf  -> csrf.disable());
		.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.authorizeHttpRequests(request ->  request.requestMatchers("/api/usuarios/auth/**").permitAll()
				.anyRequest().authenticated()
				);
				
		return http.build();
		
	}
	
 
}
