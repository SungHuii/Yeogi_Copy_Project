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

@Slf4j
@SpringBootTest(classes = Main.class)
@TestPropertySource(properties = "jwt.secret=eW91ci12ZXJ5LXNlY3VyZS1zZWNyZXQta2V5LXlvdXItdmVyeS1zZWN1cmUtc2VjcmV0LWtleQ==")
class JwtUtilTest {

   @Value("{jwt.secret}")
   private String key;

   @Autowired
   private JwtUtil jwtUtil;

   @Test
   public void generateToken() {

      // given
      // 테스트용 로그인 아이디
      String loginId = "testUser";
      MemberRole role = MemberRole.USER;
      log.info("Login ID: {}, Role: {}", loginId, role);

      // when
      // JWT 토큰 생성
      String token = jwtUtil.generateToken(loginId, role);

      // then
      // JWT 토큰 유효성 검사
      log.info("Generated token: {}", token);
      assertNotNull(token);
      assertTrue(jwtUtil.validateToken(token));


      assertEquals(loginId, jwtUtil.extractEmail(token));
      assertNotNull(token);
      assertTrue(jwtUtil.validateToken(token));


      assertEquals(loginId, jwtUtil.extractEmail(token));
   }


}