package copy.project.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */

@Entity
@Getter
@Setter
public class Coupon extends CommonEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "discount_amount", nullable = false)
    private int discountAmount;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

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
