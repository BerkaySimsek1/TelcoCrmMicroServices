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

    private static final String[] WHITE_LIST= {
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

    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req->req.requestMatchers(WHITE_LIST).permitAll())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity;
    }
}
