package copy.project.demo.dto;

import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberRole;
import copy.project.demo.entity.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 24.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String loginId; // 이메일 또는 SNS 로그인 ID
    private String password; // 이메일 회원가입 시 사용, SNS 로그인은 null
    private MemberType type;
    private String name;
    private String nickname;
    private String phone;
    private MemberGender gender;
    private LocalDate birthDate;
    private MemberRole role;
    private String token;

    /* 회원정보 조회용 생성자 */
    public MemberDTO(Long id, MemberType type, String name, String nickname, String phone, MemberGender gender, LocalDate birthDate) {
    }
}
