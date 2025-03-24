package com.elitewings_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica CORS a todos los endpoints
                        .allowedOrigins("http://localhost:5174", "http://localhost:5174/flights") // Permite este origen
                        .allowedMethods(
                                HttpMethod.POST.name(),
                                HttpMethod.GET.name(),
                                HttpMethod.DELETE.name(),
                                HttpMethod.OPTIONS.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.PATCH.name()
                        )
                        .allowedHeaders(
                                HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION
                        );
            }
        };
    }
}
