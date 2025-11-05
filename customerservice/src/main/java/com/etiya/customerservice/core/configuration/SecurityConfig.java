package com.etiya.customerservice.core.configuration;

import com.etiya.common.configuration.BaseSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final BaseSecurityService baseSecurityService;

    public SecurityConfig(BaseSecurityService baseSecurityService) {
        this.baseSecurityService = baseSecurityService;
    }

    // Commonda uyguladığımız white listi burada uyguladık
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCoreSecurity(http);

        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}