package copy.project.demo.security;

import copy.project.demo.entity.common.JwtUtil;
import copy.project.demo.entity.enums.MemberRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = extractToken(request);

        if (token != null && jwtUtil.validateToken(token)) {
            String loginId  = jwtUtil.extractEmail(token);
            MemberRole role = jwtUtil.extractRole(token);

            // 사용자 권한 확인 로직을 추가
            UsernamePasswordAuthenticationToken authenticationToken =
                  new UsernamePasswordAuthenticationToken(loginId, null, AuthorityUtils.createAuthorityList("ROLE_" + role));

            // 인증된 사용자를 SecurityContext에 추가
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }

    // 토큰 추출
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 부분을 제거한 토큰 반환
        }
        return null;
    }
}