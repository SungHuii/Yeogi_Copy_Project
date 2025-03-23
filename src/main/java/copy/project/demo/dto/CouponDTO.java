package copy.project.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 쿠폰 DTO */
@Getter
@RequiredArgsConstructor
public class CouponDTO {

    private final Long id; // 식별자 값
    private final Long memberId; // 연결된 회원 식별자 값
    private final String code; // 쿠폰 코드
    private final int discountAmount; // 할인률 or 할인가격
    private final LocalDateTime expiryDate; // 만료 날짜
}
