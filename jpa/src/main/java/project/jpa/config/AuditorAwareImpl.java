package project.jpa.config;

import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    private final HttpServletRequest servlet;

    public AuditorAwareImpl(HttpServletRequest servlet) {
        this.servlet = servlet;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        String userId = servlet.getParameter("userId");
        return Optional.of(userId);
    }
}