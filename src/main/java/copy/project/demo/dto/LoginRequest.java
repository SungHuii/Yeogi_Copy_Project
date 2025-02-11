package copy.project.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동 생성 (JSON 파싱)
public class LoginRequest {

    private String loginId;
    private String password;
}
