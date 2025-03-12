package copy.project.demo.common;

import copy.project.demo.entity.Member;
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

/* 공통 JWT 유틸 클래스 */
@Slf4j // 로그 출력용
@Component // 컴포넌트(해당 클래스를 스프링 빈(bean) 객체로 사용)로 등록. 의존성 주입
public class JwtUtil {


    private final Key key; // HMAC-SHA256 서명을 위한 비밀 키
    private final long validTime = 1000 * 60 * 60 * 24; // 토큰의 유효 기간 (24시간 유효)
    private final long accessTokenValidTime = 1000 * 60 * 30; // 엑세스 토큰 유효 기간 (30분)
    private final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 7; // 리프레시 토큰 유효 기간 (7일)

    // application.properties 에 있는 jwt.secret 값 주입
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        // secretKey 값을 Byte 배열로 변환 후 HMAC-SHA256 암호화 키 생성
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /* JWT 토큰 생성 */
    public String generateToken(String loginId, MemberRole role) {
        return Jwts.builder() // 새로운 JWT 토큰 생성
              .claim("loginId", loginId) // 사용자의 로그인 아이디를 클레임으로 추가
              .claim("role", role) // 사용자 역할을 클레임으로 추가
              .setIssuedAt(new Date()) // 현재 시간을 발급 시간으로 설정
              .setExpiration(new Date(System.currentTimeMillis() + validTime)) // 현재 시간에서 24시간 후를 만료시간으로 설정
              .signWith(key) // 비밀 키로 서명
              .compact(); // 최종적으로 JWT 문자열 반환
    }

    /* 엑세스 토큰 생성 */
    public String generateAccessToken(Member member) {
        return Jwts.builder()
                .claim("loginId", member.getLoginId())
                .claim("role", member.getRole().name()) // enum 타입은 name()으로 문자열로 변환
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidTime))
                .signWith(key)
                .compact();
    }

    /* 리프레시 토큰 생성 */
    public String generateRefreshToken(Member member) {
        return Jwts.builder()
                .claim("loginId", member.getLoginId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidTime))
                .signWith(key)
                .compact();
    }

    /* 토큰을 파싱(해석)해서 Claims(데이터)를 가져오는 메서드 */
    private Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder() // JWT 파서 빌더 생성
              .setSigningKey(key) // 비밀 키를 설정해서 서명 검증
              .build() // 파서 빌더로 파서 생성
              .parseClaimsJws(token) // 토큰을 파싱해서 JWS(서명된 JWT) 반환
              .getBody(); // JWS에서 데이터(Claims) 추출하여 반환
    }

    /* 토큰에서 아이디 추출 */
    public String extractEmail(String token) {
        Claims claims = parseToken(token); // 토큰 파싱 메서드를 사용해 Claims 추출
        return claims.get("loginId", String.class); // 파싱한 데이터에서 loginId 클레임을 String 으로 추출
    }

    /* 토큰에서 역할 추출 */
    public MemberRole extractRole(String token) {
        Claims claims = parseToken(token); // 토큰 파싱 메서드를 사용해 Claims 추출
        return claims.get("role", MemberRole.class); // 파싱한 데이터에서 role 클레임을 MemberRole 으로 추출
    }

    /* 토큰 유효성 검사 */
    public boolean validateToken(String token) {
        try {
            parseToken(token); // 토큰 파싱 메서드로 토큰 검증
            return true; // 유효한 토큰일 때 true 반환
        } catch (ExpiredJwtException e) { // 만료된 토큰 예외 처리
            log.warn("토큰 만료 : {}", e.getMessage()); // 로그 출력
        } catch (JwtException e) { // 그 외의 JWT 예외 처리
            log.warn("유효하지 않은 토큰 : {}", e.getMessage()); // 로그 출력
        }
        return false; // 유효하지 않은 토큰일 때 false 반환
    }
}
