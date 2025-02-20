package copy.project.demo.dto;

import copy.project.demo.entity.enums.PaymentMethod;
import copy.project.demo.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 2. 20.
 */
/* 결제 정보 DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id; // 결제 식별자
    private Long reservationId; // 예약 식별자
    private BigDecimal price; // 결제 금액
    private PaymentMethod paymentMethod; // 결제 수단
    private PaymentStatus paymentStatus; // 결제 상태
    private String transactionId; // 결제 고유 번호
    private LocalDateTime createdAt; // 결제 생성 시간
    private LocalDateTime updatedAt; // 결제 정보 수정 시간
}