package com.etiya.basketservice.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.etiya.common.jwt.JwtService;

import java.util.List;

@Configuration
public class FeignAuthConfig {

    private final JwtService jwtService;

    public FeignAuthConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public RequestInterceptor authForwardingInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String bearer = resolveIncomingBearer();
                if (bearer == null) {
                    // Kullanıcı token'ı yoksa servis-kimliğine dayalı internal token üret
                    String internal = jwtService.generateToken(
                            "basketservice", List.of("INTERNAL"));
                    bearer = "Bearer " + internal;
                }
                template.header(HttpHeaders.AUTHORIZATION, bearer);
            }

            private String resolveIncomingBearer() {
                try {
                    RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
                    if (!(attrs instanceof ServletRequestAttributes sra)) return null;
                    HttpServletRequest req = sra.getRequest();
                    String h = req.getHeader(HttpHeaders.AUTHORIZATION);
                    if (h != null && h.startsWith("Bearer ")) return h;
                } catch (Exception ignored) {}
                return null;
            }
        };
    }
}
