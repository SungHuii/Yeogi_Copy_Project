package copy.project.demo.entity.common;

import copy.project.demo.entity.enums.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
/**
 * Created by SungHui on 2025. 2. 6.
 */
@Slf4j // 토큰 유효성 검사 실패 시 로그 출력용
@Component
public class JwtUtil {

    private final Key key;
    private final long validTime = 1000 * 60 * 60 * 24; // 24시간 유효

    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        // HMAC-SHA256 키 생성
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /* JWT 토큰 생성 */
    public String generateToken(String loginId, MemberRole role) {
        return Jwts.builder()
              .claim("loginId", loginId) // 로그인 ID claims
              .claim("role", role)
              .setIssuedAt(new Date()) // 발급 시간
              .setExpiration(new Date(System.currentTimeMillis() + validTime)) // 만료시간 설정
              .signWith(key)
              .compact();
    }

    // 토큰 파싱해서 Claims 를 가져오는 메서드
    private Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(token)
              .getBody();
    }

    /* 토큰에서 아이디 추출 */
    public String extractEmail(String token) {
        Claims claims = parseToken(token);
        return claims.get("loginId", String.class);
    }

    /* 토큰에서 역할 추출 */
    public MemberRole extractRole(String token) {
        Claims claims = parseToken(token);
        return claims.get("role", MemberRole.class);
    }

    /* 토큰 유효성 검사 */
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("토큰 만료 : {}", e.getMessage());
        } catch (JwtException e) {
            log.warn("유효하지 않은 토큰 : {}", e.getMessage());
        }
        return false;
    }
}
