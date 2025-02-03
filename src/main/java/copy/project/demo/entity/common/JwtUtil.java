package copy.project.demo.entity.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j // 토큰 유효성 검사 실패 시 로그 출력용
@Component
public class JwtUtil {

    private final Key key;
    private final long validTime = 1000 * 60 * 60 * 24; // 24시간 유효

    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        // 환경 변수에서 가져온 secretKey를 Base64 디코딩하여 Key 생성
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    /* JWT 토큰 생성 */
    public String generateToken(String loginId) {
        return Jwts.builder()
                .subject(loginId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + validTime))
                .signWith(key)
                .compact();
    }

    /* 토큰에서 아이디 추출 */
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key) // key를 SecretKey로 캐스팅
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /* 토큰 유효성 검사 */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key)  // key를 SecretKey로 캐스팅
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    /* JWT 토큰에서 Claims 추출 */
    public Claims getClaims(String token) {
        JwtParser parser = Jwts.builder()
                (key)
                .build();
        return parser.parseClaimsJws(token).getBody();
    }

}
