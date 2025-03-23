package copy.project.demo.dto;

import copy.project.demo.entity.enums.PaymentMethod;
import copy.project.demo.entity.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 2. 20.
 */
/* 결제 정보 DTO */
@Getter
@NoArgsConstructor(force = true)  // 기본 생성자 추가 (final 필드 강제 초기화)
@RequiredArgsConstructor
public class PaymentDTO {

    private final Long id; // 결제 식별자
    private final Long reservationId; // 예약 식별자
    private final BigDecimal price; // 결제 금액
    private final PaymentMethod paymentMethod; // 결제 수단
    private final PaymentStatus paymentStatus; // 결제 상태
    private final String transactionId; // 결제 고유 번호
    private final LocalDateTime createdAt; // 결제 생성 시간
    private final LocalDateTime updatedAt; // 결제 정보 수정 시간
}