package com.etiya.authservice.configuration;

import com.etiya.common.configuration.BaseSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static com.etiya.common.configuration.BaseSecurityService.WHITE_LIST;

@Configuration
public class SecurityConfig {

    private final BaseSecurityService baseSecurityService;

    public SecurityConfig(BaseSecurityService baseSecurityService) {
        this.baseSecurityService = baseSecurityService;
    }

    // Commonda uyguladığımız white listi burada uyguladık
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        baseSecurityService.configureCoreSecurity(httpSecurity);
        httpSecurity.authorizeHttpRequests
                (requests -> requests.requestMatchers(WHITE_LIST).permitAll().anyRequest().authenticated());
        return httpSecurity.build();
    }
}
