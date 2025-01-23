package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import copy.project.demo.entity.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity
@Getter
@Setter
public class Reservation extends CommonEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "accommodation_room_id", nullable = false)
    private AccommodationRoom accommodationRoom;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Column(name = "guest_count", nullable = false)
    private int guestCount;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;


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
}
