package copy.project.demo.library;

import copy.project.demo.entity.common.JwtUtil;
import copy.project.demo.security.JwtAuthenticationFilter;
import copy.project.demo.security.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
        return http.getSharedObject(AuthenticationManagerBuilder.class).build(); // AuthenticationManager 빈 설정
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // CSRF 보호를 비활성화 (필요한 경우 변경)
                .authorizeRequests() // 새로운 방식으로 권한 설정
                .requestMatchers("/api/auth/**").permitAll() // 인증이 필요 없는 API
                .anyRequest().authenticated() // 나머지는 인증 필요
                .and().addFilter(new JwtAuthenticationFilter(authenticationManager(http), jwtUtil)) // JWT 인증 필터
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), JwtAuthenticationFilter.class); // JWT 권한 필터

        return http.build(); // 새로운 방식으로 필터 체인 빌드
    }
}
