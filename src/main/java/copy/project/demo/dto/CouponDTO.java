package copy.project.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 쿠폰 DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {

    private Long id; // 식별자 값
    private Long memberId; // 연결된 회원 식별자 값
    private String code; // 쿠폰 코드
    private int discountAmount; // 할인률 or 할인가격
    private LocalDateTime expiryDate; // 만료 날짜
}
