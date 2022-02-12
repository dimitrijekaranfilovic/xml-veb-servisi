package rs.vakcinacija.sluzbenici.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO: Allow only requests from sluzbenici-frontend application
        registry.addMapping("/**").allowedMethods("*");
    }
}

