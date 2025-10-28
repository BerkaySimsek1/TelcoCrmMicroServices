package com.etiya.common.filters;

import com.etiya.common.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Gelen HTTP isteğinin (request) "Authorization" isimli başlığını (header) oku.
        // (Genellikle "Bearer eyJhbGciOiJI..." gibi bir değer içerir)
        String jwtHeader = request.getHeader("Authorization");
        // Başlığın boş olmadığını (null) VE "Bearer " metni ile başladığını kontrol et.
        // ("Bearer " standard bir token ön ekidir)
        if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            String token = jwtHeader.substring("Bearer ".length());
            // JWT servisimiz aracılığıyla token'ın içinden "username" (kullanıcı adı) bilgisini çıkar.
            String username = jwtService.extractUsername(token);
            // Benzer şekilde, token'ın içinden kullanıcının "rollerini" (örn: ["ADMIN", "USER"]) çıkar.
            List<String> roles = jwtService.extractRoles(token);
            // Spring Security'nin anlayacağı formata (GrantedAuthority) rolleri dönüştür.
            // ["ADMIN"] listesini -> [new SimpleGrantedAuthority("ADMIN")] listesine çevirir.
            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
            // JWT servisimizle token'ın geçerli olup olmadığını kontrol et.
            // (İmzası doğru mu? Süresi dolmuş mu? Kullanıcı adı eşleşiyor mu?)
            if (jwtService.validateToken(token, username)) {
                // Kullanıcı adı, parola (artık gerek yok, o yüzden null) ve yetkileri (rolleri) içeren
                // bir Spring Security kimlik doğrulama nesnesi oluştur.
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                // Kimlik doğrulama nesnesine istekle ilgili detayları (örn: IP adresi) ekle.
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // En önemli adım: Spring Security'nin mevcut güvenlik bağlamına (Context)
                // "Bu kullanıcıyı artık tanıyoruz (authenticate ettik)" diye bu nesneyi yerleştir.
                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);
            }
        }
        // Bu kod bir filtre olduğu için, işlem bittikten sonra isteğin
        // filtre zincirindeki bir sonraki adıma devam etmesine izin ver.
        filterChain.doFilter(request, response);
    }
}
