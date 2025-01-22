package copy.project.demo.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity
public class Member { // 회원 정보

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    private String name;

    private String nickname;

    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberGender gender;

    /* 시간 정보 없이 날짜(yyyy-MM-dd)만 표현 가능 */
    private LocalDate birthDate;

    @CreationTimestamp
    private LocalDateTime createdAt; // 회원 등록 일자



    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MemberGender getGender() {
        return gender;
    }

    public void setGender(MemberGender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // toStringx`
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
