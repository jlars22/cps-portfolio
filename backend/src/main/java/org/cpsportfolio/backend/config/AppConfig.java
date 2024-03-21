package org.cpsportfolio.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String weatherApiKey;

    @Bean
    public WebMvcConfigurer corsConfigurer(Environment environment) {
        String frontendUrl = environment.getProperty("FRONTEND_BASE_URL");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(frontendUrl);
            }
        };
    }
}
