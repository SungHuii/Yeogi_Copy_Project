package copy.project.demo.security;

import copy.project.demo.entity.common.JwtUtil;
import copy.project.demo.entity.enums.MemberRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * Created by SungHui on 2025. 2. 6.
 */
/* 클라이언트가 API 요청 시 JWT를 포함해서 보낼 때 인증을 수행하기 위한 필터
*  JWT를 검증하고 사용자 인증 정보를 SecurityContext에 저장하는 역할
*  JwtAuthenticationFilter에서 토큰 발급 후에는 이 필터에서 모든 요청에 대한 JWT 기반 인증을 수행
* */
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter /* 모든 요청마다 JWT 검사 */{

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // 모든 요청마다 JWT를 검사하는 로직을 실행하기 위한 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 요청 헤더에서 JWT 추출
        String token = extractToken(request);

        // 토큰이 유효할 경우 사용자 정보 추출
        if (token != null && jwtUtil.validateToken(token)) {
            String loginId  = jwtUtil.extractEmail(token); // 로그인 아이디 추출
            MemberRole role = jwtUtil.extractRole(token); // 사용자 권한 추출

            // 사용자 정보를 DB에서 가져와 UserDetails 객체 생성
            var userDetails = userDetailsService.loadUserByUsername(loginId);

            // 사용자 권한 확인 로직 추가. Spring Security에서 사용할 인증 객체
            UsernamePasswordAuthenticationToken authenticationToken =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // 인증된 사용자를 SecurityContext에 추가. 이후의 요청에서 사용자 정보를 참조할 수 있도록 함
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // 필터 체인을 통해 다음 필터로 요청을 넘김
        chain.doFilter(request, response);
    }

    // JWT를 추출하는 메서드
    private String extractToken(HttpServletRequest request) {
        // 요청 헤더에서 JWT 토큰을 가져옴
        String bearerToken = request.getHeader("Authorization");
        // "Bearer " 접두사가 붙어있는지 확인 후 토큰 반환
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 부분을 제거한 토큰 반환
        }
        return null;
        /* 여기서 null을 반환하는 이유는 토큰이 없거나 잘못된 경우에는 인증을 수행하지 않고 다음 필터로 요청을 넘기기 위함
         null을 반환하지 않고 예외를 발생시킬 경우 인증이 필요 없는 요청에서도 401 에러가 발생할 수 있음 */
    }
}