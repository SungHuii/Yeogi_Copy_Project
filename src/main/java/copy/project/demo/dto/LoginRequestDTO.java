package copy.project.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by SungHui on 2025. 2. 11.
 */

/* 회원의 id, pw를 DTO로 전달하여 로그인 기능을 구현하기 위한 DTO */
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동 생성 (JSON 파싱)
public class LoginRequestDTO {

    private String loginId; // 회원 아이디
    private String password; // 회원 비밀번호
}
