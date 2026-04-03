package com.aigateway.app.config;

import com.aigateway.app.security.ApiKeyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(ex -> ex
                        .pathMatchers("/ai/**").permitAll()
                        .anyExchange().permitAll()
                )
                .addFilterAt(new ApiKeyFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}