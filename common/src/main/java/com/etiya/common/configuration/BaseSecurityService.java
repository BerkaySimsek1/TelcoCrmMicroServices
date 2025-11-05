package com.etiya.common.configuration;

import com.etiya.common.filters.JwtAuthFilter;
import jakarta.servlet.http.HttpServletResponse;
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

    public static final String[] WHITE_LIST = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/auth/**",
//            "/api/catalogs/**",
//            "/api/products/**",
//            "/api/product-spec/**",
//            "/api/campaign-products/**",
//            "/api/product-offers/**",
//            "/api/campaigns/**"
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
                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST).permitAll())

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("""
                                        {
                                            "status": 401,
                                            "error": "Unauthorized",
                                            "message": "Kimlik doğrulaması yapılmamış veya token eksik."
                                        }
                                    """);
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json;charset=UTF-8");
                            response.getWriter().write("""
                                        {
                                            "status": 403,
                                            "error": "Forbidden",
                                            "message": "Bu işlemi yapmaya yetkiniz yok."
                                        }
                                    """);
                        })
                )

                // DİKKAT: Diğer tüm isteklerin kimlik doğrulaması gerektirdiğini
                // her servisin kendi SecurityConfig'inde belirtmesi daha doğrudur.
                // Bu satırı buradan kaldırıp, her servisin kendi config'ine ekleyelim.
                // .anyRequest().authenticated() // <= BU SATIRI SİL


                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity;
    }
}
