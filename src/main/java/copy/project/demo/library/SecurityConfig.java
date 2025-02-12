package copy.project.demo.library;

import copy.project.demo.entity.common.JwtUtil;
import copy.project.demo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by SungHui on 2025. 2. 6.
 */
/*
*  Security 설정 클래스
* */
@Configuration // 설정 파일 클래스 어노테이션
@EnableWebSecurity // 웹 보안(Spring Security) 활성화 어노테이션
@RequiredArgsConstructor // final 필드 생성자 자동 생성가능
public class SecurityConfig {

    private final JwtUtil jwtUtil; // JWT 생성, 검증 담당 유틸
    private final UserDetailsService userDetailsService; // 사용자 정보 서비스. DB에서 사용자 정보를 가져옴

    // passwordEncoder를 사용하면 비밀번호를 암호화해서 저장 가능
    // 회원가입 시 비밀번호를 암호화해서 DB 저장
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 암호화 빈 등록
    }

    // JWT 로그인 요청 처리 인증 필터
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(HttpSecurity http) throws Exception{
        return new JwtAuthenticationFilter(jwtUtil, authenticationManager(http));
        // authenticationManager : 사용자 인증을 담당하는 객체
        // JWT 생성 및 검증 기능을 필터에 전달
    }

    // 사용자 인증 관리자 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
              .userDetailsService(userDetailsService) // DB에서 사용자 정보를 가져오는 서비스 설정
              .passwordEncoder(passwordEncoder()) // 비밀번호를 비교할 때 위의 passwordEncoder()로 암호화된 비밀번호를 비교
              .and()
              .build(); // AuthenticationManager 객체 생성 후 반환
    }

    // 사용자 권한 체크, 보안 필터 체인 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // HttpSecurity : 보안 규칙 설정
        http.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화 (JWT를 사용하면 CSRF 보호가 필요 없음)
                .authorizeHttpRequests(auth -> auth // security 처리에 HttpServletRequest 사용
                        .requestMatchers("/members/**").permitAll() // /members/** 경로는 인증 없이 접근 가능
                        .requestMatchers("/members/admin/**").hasRole("ADMIN") // 관리자만 접근 가능한 경로
                        .anyRequest().authenticated()); // 나머지는 인증된 사용자만 접근 가능

        return http.build(); // SecurityFilterChain 반환
    }
}
