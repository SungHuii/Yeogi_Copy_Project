package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberRole;
import copy.project.demo.entity.enums.MemberType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity // JPA 엔티티로 등록
@Getter // Lombok Getter 자동 생성
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(name = "UK_member_loginId", columnNames = "login_id") // 회원가입 ID 중복 방지 제약 조건
})
public class Member extends CommonEntity { // 회원 정보

    // 회원 식별자 값
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 회원 가입 아이디
    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    // 회원 비밀번호
    // 이메일 회원가입 비밀번호 (SNS 로그인은 사용 안하기 때문에 nullable 적용 안함)
    @Column(name = "password")
    private String password;

    // 회원가입 타입 (이메일, 카카오, 구글, 네이버)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MemberType type;

    // 회원 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 회원 닉네임
    @Column(name = "nickname", nullable = false)
    private String nickname;

    // 회원 전화번호
    @Column(name = "phone", nullable = false)
    private String phone;

    // 회원 성별 (M, F)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private MemberGender gender;

    // 회원 생년월일
    /* 시간 정보 없이 날짜(yyyy-MM-dd)만 표현 가능 */
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    // 회원 권한(USER, ADMIN)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    // 회원 정보 수정용
    public Member copy(String nickname, String phone) {
        return new Member(
                this.id,
                this.loginId,
                this.password,
                this.type,
                this.name,
                nickname,
                phone,
                this.gender,
                this.birthDate,
                this.role
        );
    }


    // toString`
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                '}';
    }
}