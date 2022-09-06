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
        config.setAllowCredentials(false);
//        config.addAllowedOriginPattern("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("DELETE");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Memberid");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
