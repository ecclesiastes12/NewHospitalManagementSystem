package com.hms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//disable csrf(cross site request forgery) token
		http.csrf(csrf -> csrf.disable());
		
	
		
		http.authorizeHttpRequests((requests) -> { requests
         .requestMatchers("/api/v1/users").permitAll() 
         .anyRequest().authenticated();
		});
		
		
		
        
		// http.formLogin(Customizer.withDefaults());   
		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}

}
