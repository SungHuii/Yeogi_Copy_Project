package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import copy.project.demo.entity.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 22.
 */
/* 연관 관계
 *  예약 : 회원 = N : 1
 *  예약 : 숙소 객실 = N : 1
 *  예약 엔티티
 * */
@Entity
@Getter
@RequiredArgsConstructor
public class Reservation extends CommonEntity {

    // 예약 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    // 회원 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "member_id", nullable = false) // 외래키
    private final Member member;

    // 숙소 객실 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "accommodation_room_id", nullable = false) // 외래키
    private final AccommodationRoom accommodationRoom;

    // 예약 날짜
    @Column(name = "reservation_date", nullable = false)
    private final LocalDate reservationDate;

    // 체크인 날짜
    @Column(name = "check_in", nullable = false)
    private final LocalDate checkIn;

    // 체크아웃 날짜
    @Column(name = "check_out", nullable = false)
    private final LocalDate checkOut;

    // 예약 인원
    @Column(name = "guest_count", nullable = false)
    private final int guestCount;

    // 총 가격
    @Column(name = "total_price", nullable = false)
    private final int totalPrice;

    // 예약 상태 (보류, 확정, 취소)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private final ReservationStatus status;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", member=" + member +
                ", accommodationRoom=" + accommodationRoom +
                ", reservationDate=" + reservationDate +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", guestCount=" + guestCount +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }


    public Reservation cancel() {
        return new Reservation(
                this.id,
                this.member,
                this.accommodationRoom,
                this.reservationDate,
                this.checkIn,
                this.checkOut,
                this.guestCount,
                this.totalPrice,
                ReservationStatus.CANCELED
        );
    }
}