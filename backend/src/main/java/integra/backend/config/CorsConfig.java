package integra.backend.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Applies to ALL endpoints (/registrations, /events, etc)
                        .allowedOrigins("http://localhost:4200") // Allows your Angular app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // MUST allow OPTIONS for preflights!
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
