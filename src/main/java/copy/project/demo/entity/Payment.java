package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import copy.project.demo.entity.enums.PaymentMethod;
import copy.project.demo.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by SungHui on 2025. 2. 19.
 */
/* 결제 정보 관련 엔티티 */
@Entity
@Getter
@RequiredArgsConstructor
public class Payment extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id; // 결제 id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private final Reservation reservation; // 예약 정보

    @Column(nullable = false)
    private final BigDecimal price; // 결제 금액

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final PaymentMethod paymentMethod; // 결제 수단

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final PaymentStatus paymentStatus; // 결제 상태

    @Column(nullable = false)
    private final String transactionId; // 결제 고유 번호

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", price=" + price +
                ", paymentMethod=" + paymentMethod +
                ", paymentStatus=" + paymentStatus +
                ", transactionId='" + transactionId + '\'' +
                ", createdBy='" + getCreatedBy() + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", updatedBy='" + getUpdatedBy() + '\'' +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}