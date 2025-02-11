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
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 암호화 빈 등록
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(HttpSecurity http) throws Exception{
        return new JwtAuthenticationFilter(authenticationManager(http), jwtUtil);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
              .userDetailsService(userDetailsService)
              .passwordEncoder(passwordEncoder())
              .and()
              .build();// AuthenticationManager 빈 설정
    }

    // 사용자 권한 체크, 필터체인.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
                .authorizeHttpRequests(auth -> auth // security 처리에 HttpServletRequest 사용
                        .requestMatchers("/members/**").permitAll() // 인증 없이 접근 가능
                        .requestMatchers("/members/admin/**").hasRole("ADMIN") // 관리자만 접근 가능한 uri
                        .anyRequest().authenticated()); // 나머지는 인증 필요

        return http.build(); // SecurityFilterChain 반환
    }
}
