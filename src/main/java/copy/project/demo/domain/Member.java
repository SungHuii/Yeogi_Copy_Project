package copy.project.demo.domain;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity
public class Member { // 회원 정보

    @Id @GeneratedValue
    @Column(name = "member_id")
    private int id;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Column(name = "member_name")
    private String name;

    private String nickname;

    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberGender gender;

    private Date birthDate;


    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    // toString
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
