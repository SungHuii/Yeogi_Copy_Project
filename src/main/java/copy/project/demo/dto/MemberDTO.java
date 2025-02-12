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

/* 회원 정보 DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id; // 식별자 값
    private String loginId; // 이메일 또는 SNS 로그인 ID
    private String password; // 이메일 회원가입 시 사용, SNS 로그인은 null
    private MemberType type; // 회원 타입 (email, 카카오, 네이버, 구글)
    private String name; // 회원 이름
    private String nickname; // 회원 닉네임
    private String phone; // 회원 전화번호 (국제 번호를 포함하기 위한 String 타입 구성)
    private MemberGender gender; // 회원 성별
    private LocalDate birthDate; // 회원 생일
    private MemberRole role; // USER, ADMIN
    private String token; // JWT 토큰값

    /* 회원정보 조회용 생성자 */
    public MemberDTO(
            Long id,
            MemberType type,
            String name,
            String nickname,
            String phone,
            MemberGender gender,
            LocalDate birthDate)
    { }
}
