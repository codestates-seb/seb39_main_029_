package codestates.preproject.stackoverflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000/ask","http://localhost:3001/ask","http://localhost:3000","http://localhost:3001"));
        config.addAllowedOrigin("http://localhost:3001/signup");
        config.addAllowedOrigin("http://localhost:3000/signup");
        config.addAllowedOrigin("http://localhost:3001/home");
        config.addAllowedOrigin("http://localhost:3000/home");
        config.addAllowedOrigin("http://localhost:3001/edit");
        config.addAllowedOrigin("http://localhost:3000/edit");
        config.addAllowedOrigin("http://localhost:3001/question");
        config.addAllowedOrigin("http://localhost:3000/question");
        config.addAllowedOrigin("http://localhost:3001/tag");
        config.addAllowedOrigin("http://localhost:3000/tag");
        config.addAllowedOrigin("http://localhost:3001/login");
        config.addAllowedOrigin("http://localhost:3000/login");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("DELETE");
        config.addExposedHeader("Authorization");
        config.setAllowCredentials(true);
        config.addExposedHeader("Set-Cookie");
        config.addExposedHeader("Memberid");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
