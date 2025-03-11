package copy.project.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Favorite {

    protected Favorite () {
        this.id = null;
        this.member = null;
        this.accommodation = null;
    }

    // 찜 목록 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 회원 정보
    @ManyToOne // 다대일
    @JoinColumn(name = "member_id", nullable = false) // 외래키
    private Member member;

    // 숙소 정보
    @ManyToOne // 다대일
    @JoinColumn(name = "accommodation_id", nullable = false) // 외래키
    private Accommodation accommodation;

    // 생성자에서만 값 설정
    public Favorite(Member member, Accommodation accommodation) {
        this.member = member;
        this.accommodation = accommodation;
    }

    // 찜 추가
    public static Favorite createFavorite(Member member, Accommodation accommodation) {
        return new Favorite(member, accommodation);
    }





    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", member=" + member +
                ", accommodation=" + accommodation +
                '}';
    }
}