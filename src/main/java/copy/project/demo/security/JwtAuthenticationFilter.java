package copy.project.demo.security;

import copy.project.demo.entity.common.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created by SungHui on 2025. 2. 2.
 */
/* 클라이언트가 API 요청 시 JWT를 포함해서 보낼 때 인증을 수행하기 위한 필터
*  로그인 시 ID, PW 기반 인증을 먼저 검증 후 JWT 토큰을 발급.
* */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter /* 요청당 한번만 실행하는 필터 */ {

    private final JwtUtil jwtUtil; // JWT 토큰을 생성, 검증하고 사용자 정보를 추출하는 유틸
    private final AuthenticationManager authenticationManager; // Spring Security에서 사용자 인증을 처리하는 객체

    // JWT 토큰 검증 및 인증 설정
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
            ServletException, IOException { // 모든 요청마다 실행되는 메서드(필터)
        // 요청 헤더에서 JWT를 확인하고, 유효한 토큰일 때 SecurityContextHolder에 사용자 인증 정보 저장

        // 요청 헤더에서 Authorization 헤더값을 가져옴
        String authHeader = request.getHeader("Authorization");
        // "Bearer "로 시작하지 않으면 필터를 통과시키고 그대로 다음 필터로 전달 (JWT가 없거나 잘못된 경우 인증 없이 요청 진행)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // "Bearer " 이후의 토큰만 추출
        String token = authHeader.substring(7);
        if (jwtUtil.validateToken(token)) { // 토큰이 유효한지 검증
            String email = jwtUtil.extractEmail(token); // 토큰에서 이메일 추출

            // 사용자 정보 생성. email을 사용자명으로 설정, 비밀번호는 빈 문자열(검증X), USER 권한 부여
            UserDetails userDetails = User.withUsername(email).password("").roles("USER").build();
            // 사용자 인증 토큰을 생성하여 Spring Security의 인증 객체로 변환
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 요청 정보를 인증 객체에 저장
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            /* 토큰이 유효할 시 사용자 정보 저장
            *  SecurityContextHolder : Spring Security 에서 사용자 정보를 저장하는 객체
            *  이 후의 요청에서 @AuthenticationPrincipal 어노테이션으로 사용자 정보 조회 가능
            *  ( 현재 로그인한 사용자를 가져오는 메서드 등으로 컨트롤러, 서비스에서 활용 가능
            * */
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 현재 필터에서 인증 처리를 마치고 다음 필터로 요청을 전달
        chain.doFilter(request, response);
    }
}
