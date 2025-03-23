package copy.project.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class KakaoUserInfo {

    private String email;
    private String name;
    private String nickname;
    private String phone;
    private String gender;
    private LocalDate birthDate;
}
