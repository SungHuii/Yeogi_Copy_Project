package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import copy.project.demo.entity.enums.PaymentMethod;
import copy.project.demo.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by SungHui on 2025. 2. 19.
 */
/* 결제 정보 관련 엔티티 */
@Entity
@Getter
@NoArgsConstructor
public class Payment extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 결제 id;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation; // 예약 정보

    @Column(nullable = false)
    private BigDecimal price; // 결제 금액

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod; // 결제 수단

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus; // 결제 상태

    @Column(nullable = false)
    private String transactionId; // 결제 고유 번호

    //명시적 생성자
    public Payment(Long id, Reservation reservation, BigDecimal price,
                   PaymentMethod paymentMethod, PaymentStatus paymentStatus, String transactionId) {
        this.id = id;
        this.reservation = reservation;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
    }

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