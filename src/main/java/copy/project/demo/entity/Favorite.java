package copy.project.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 22.
 */
/* 연관 관계
 *  찜목록 : 회원 = N : 1
 *  찜목록 : 숙소 = N : 1
 * */
/* 찜 목록 엔티티 */
@Entity
@Getter
@RequiredArgsConstructor
public class Favorite {

    protected Favorite () {
        this.id = null;
        this.member = null;
        this.accommodation = null;
    }

    // 찜 목록 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    // 회원 정보
    @ManyToOne // 다대일
    @JoinColumn(name = "member_id", nullable = false) // 외래키
    private final Member member;

    // 숙소 정보
    @ManyToOne // 다대일
    @JoinColumn(name = "accommodation_id", nullable = false) // 외래키
    private final Accommodation accommodation;



    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", member=" + member +
                ", accommodation=" + accommodation +
                '}';
    }
}