package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends CommonEntity { // 회원 정보

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MemberType type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private MemberGender gender;

    /* 시간 정보 없이 날짜(yyyy-MM-dd)만 표현 가능 */
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    public Member(Long id, MemberType type, String name, String nickname, String phone, MemberGender gender, LocalDate birthDate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
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
