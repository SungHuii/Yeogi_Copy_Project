package copy.project.demo.dto;

import lombok.Getter;
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
>>>>>>> master

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 쿠폰 DTO */
@Getter
<<<<<<< HEAD

=======
@RequiredArgsConstructor
@NoArgsConstructor(force = true)  // 기본 생성자 추가 (final 필드 강제 초기화)
>>>>>>> master
public class CouponDTO {

    private final Long id; // 식별자 값
    private final Long memberId; // 연결된 회원 식별자 값
    private final String code; // 쿠폰 코드
    private final int discountAmount; // 할인률 or 할인가격
    private final LocalDateTime expiryDate; // 만료 날짜
}
