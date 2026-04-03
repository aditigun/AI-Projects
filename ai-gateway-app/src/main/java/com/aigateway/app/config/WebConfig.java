package com.aigateway.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.server.WebFilter;

import java.time.Duration;
import java.util.Arrays;

@Configuration
public class WebConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:4200",
                "http://localhost:3000"
        ));

        config.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    //  IMPORTANT FOR AI STREAMING
    @Bean
    public WebFluxConfigurer webFluxConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
                configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024);
            }
        };
    }

    //  REQUEST LOGGING
    @Bean
    public WebFilter loggingFilter() {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            System.out.println("[REQUEST] " + path);

            return chain.filter(exchange)
                    .doOnSuccess(aVoid ->
                            System.out.println("[RESPONSE] " + path));
        };
    }

    //  OPTIONAL TIMEOUT
    @Bean
    public WebFilter timeoutFilter() {
        return (exchange, chain) ->
                chain.filter(exchange)
                        .timeout(Duration.ofSeconds(30));
    }
}