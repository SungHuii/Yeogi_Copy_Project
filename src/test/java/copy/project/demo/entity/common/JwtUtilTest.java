package copy.project.demo.entity.common;

import copy.project.demo.Main;
import copy.project.demo.entity.enums.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Created by SungHui on 2025. 2. 6.
 */
@Slf4j
@SpringBootTest(classes = Main.class)
@TestPropertySource(properties = "jwt.secret=eW91ci12ZXJ5LXNlY3VyZS1zZWNyZXQta2V5LXlvdXItdmVyeS1zZWN1cmUtc2VjcmV0LWtleQ==")
class JwtUtilTest {

   @Value("${jwt.secret}")
   private String key;

   @Autowired
   private JwtUtil jwtUtil;

   @Test
   public void generateToken() {

      // given
      String loginId = "testUser";
      MemberRole role = MemberRole.USER;
      log.info("Login ID: {}, Role: {}", loginId, role);

      // when
      String token = jwtUtil.generateToken(loginId, role);

      // then
      log.info("Generated token: {}", token);

      // JWT 토큰 유효성 검사
      assertNotNull(token);
      assertTrue(jwtUtil.validateToken(token));

      // JWT에서 이메일(로그인 ID) 추출 및 확인
      assertEquals(loginId, jwtUtil.extractEmail(token));
   }
}
