package copy.project.demo.entity;

import copy.project.demo.common.CommonEntity;
import copy.project.demo.entity.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Reservation extends CommonEntity {

    // 예약 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 회원 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "member_id", nullable = false) // 외래키
    private Member member;

    // 숙소 객실 정보 식별자
    @ManyToOne // 다대일
    @JoinColumn(name = "accommodation_room_id", nullable = false) // 외래키
    private AccommodationRoom accommodationRoom;

    // 예약 날짜
    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    // 체크인 날짜
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    // 체크아웃 날짜
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    // 예약 인원
    @Column(name = "guest_count", nullable = false)
    private int guestCount;

    // 총 가격
    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    // 예약 상태 (보류, 확정, 취소)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    // 명시적 생성자
    public Reservation(Long id, Member member, AccommodationRoom accommodationRoom,
                       LocalDate reservationDate, LocalDate checkIn, LocalDate checkOut,
                       int guestCount, int totalPrice, ReservationStatus status) {
        this.id = id;
        this.member = member;
        this.accommodationRoom = accommodationRoom;
        this.reservationDate = reservationDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestCount = guestCount;
        this.totalPrice = totalPrice;
        this.status = status;
    }

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

    public Reservation confirmed() {
        return new Reservation(
                this.id,
                this.member,
                this.accommodationRoom,
                this.reservationDate,
                this.checkIn,
                this.checkOut,
                this.guestCount,
                this.totalPrice,
                ReservationStatus.CONFIRMED
        );
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