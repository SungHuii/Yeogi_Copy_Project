package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class Review extends CommonEntity {

    // 리뷰 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    // 회원 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "member_id", nullable = false) // 외래키
    private final Member member;

    // 숙소 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "accommodation_id", nullable = false) // 외래키
    private final Accommodation accommodation;

    // 평점 (1-5)
    @Column(name = "rating", nullable = false)
    private final byte rating;

    // 리뷰 내용
    @Column(name = "comment")
    private final String comment;

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