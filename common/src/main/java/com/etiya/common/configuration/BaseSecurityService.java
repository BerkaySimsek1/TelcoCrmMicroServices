package com.etiya.common.configuration;

import com.etiya.common.filters.JwtAuthFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class BaseSecurityService {

    private final JwtAuthFilter jwtAuthFilter;

    public BaseSecurityService(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    public static final String[] WHITE_LIST= {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/auth/**",
            "/api/catalogs/**",
            "/api/products/**",
            "/api/product-spec/**",
            /*
            "/api/individual-customers/**",
            "/api/addresses/**",
            "/api/billing-accounts/**",
            "/api/cities/**",
            "/api/contact-mediums/**",
            "/api/districts/**",
            "/api/customer-search/**"

             */
    };

    public HttpSecurity configureCoreSecurity(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http;
    }
}
