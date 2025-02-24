package copy.project.demo.entity;

import copy.project.demo.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 연관 관계
 *  회원 : 쿠폰 = 1 : N
 * */
/* 쿠폰 엔티티 */
@Entity
@Getter
@RequiredArgsConstructor
public class Coupon extends CommonEntity {

    protected Coupon () {
        this.id = null;
        this.member = null;
        this.code = null;
        this.discountAmount = 0;
        this.expiryDate = null;
    }

    // 쿠폰 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    // 회원 정보
    @ManyToOne // 연관 관계 다대일
    @JoinColumn(name = "member_id", nullable = false) // 외래키
    private final Member member;

    // 쿠폰 코드
    @Column(name = "code", nullable = false)
    private final String code;

    // 할인 금액
    @Column(name = "discount_amount", nullable = false)
    private final int discountAmount;

    // 쿠폰 만료 날짜
    @Column(name = "expiry_date", nullable = false)
    private final LocalDateTime expiryDate;

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", member=" + member +
                ", code='" + code + '\'' +
                ", discountAmount=" + discountAmount +
                ", expiryDate=" + expiryDate +
                '}';
    }
}