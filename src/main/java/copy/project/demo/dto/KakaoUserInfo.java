package copy.project.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/**
 * Created by SungHui on 2025. 3. 11.
 */
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
