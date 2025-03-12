package copy.project.demo.entity;

import copy.project.demo.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 23.
 */

/* 연관 관계
 *  리뷰 : 회원 = N : 1
 *  리뷰 : 숙소 = N : 1
 *  리뷰 엔티티
 * */
@Entity
@Getter
@NoArgsConstructor
public class Review extends CommonEntity {

    // 리뷰 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 회원 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "member_id", nullable = false) // 외래키
    private Member member;

    // 숙소 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "accommodation_id", nullable = false) // 외래키
    private Accommodation accommodation;

    // 평점 (1-5)
    @Column(name = "rating", nullable = false)
    private byte rating;

    // 리뷰 내용
    @Column(name = "comment")
    private String comment;

    // 명시적 생성자
    public Review(Long id, Member member, Accommodation accommodation, byte rating, String comment) {
        this.id = id;
        this.member = member;
        this.accommodation = accommodation;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", member=" + member +
                ", accommodation=" + accommodation +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}