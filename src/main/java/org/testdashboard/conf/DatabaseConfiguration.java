package org.testdashboard.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *
 */
@Configuration
@EnableJpaAuditing
public class DatabaseConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> "Administrator";
    }
}
