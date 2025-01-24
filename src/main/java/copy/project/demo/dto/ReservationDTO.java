package copy.project.demo.dto;

import copy.project.demo.entity.enums.ReservationStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 24.
 */
@Getter
@RequiredArgsConstructor
public class ReservationDTO {

    private final Long id;
    private final Long memberId;
    private final Long accommodationRoomId;
    private final LocalDate reservationDate;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final int guestCount;
    private final int totalPrice;
    private final ReservationStatus status;

}
