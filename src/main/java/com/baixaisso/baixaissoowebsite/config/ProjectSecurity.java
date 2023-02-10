package com.baixaisso.baixaissoowebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/admin/actuator/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/actuator/**").permitAll()
                        .anyRequest().permitAll()
                );
                http.headers().frameOptions().disable();

            return http.build();

        }


    }



