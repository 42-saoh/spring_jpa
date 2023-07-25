package project.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpServletRequest;

@Configuration
class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider(HttpServletRequest servlet) {
        return new AuditorAwareImpl(servlet);
    }
}