package copy.project.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 3. 12.
 */
@Data
@NoArgsConstructor
public class NaverUserInfo {

    private String resultCode;
    private String message;
    private Response response; // Json 형태로 받는 데이터를 담을 변수

    // Json 형태로 받는 데이터를 받을 클래스
    @Data
    public static class Response {

        private String id;
        private String email;
        private String name;
        private String nickname;
        private String phone;
        private String gender;
        private LocalDate birthDate;
        private String age;
        private String profileImage;
    }

}
