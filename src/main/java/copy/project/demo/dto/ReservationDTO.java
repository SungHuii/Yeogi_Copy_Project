package copy.project.demo.dto;

import copy.project.demo.entity.enums.ReservationStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 24.
 */
/* 예약 정보 DTO */
@Getter
@RequiredArgsConstructor
public class ReservationDTO {

    private final Long id; // 식별자 값
    private final Long memberId; // 예약자 식별자 값
    private final Long accommodationRoomId; // 숙소 방 식별자 값
    private final LocalDate reservationDate; // 예약 날짜
    private final LocalDate checkIn; // 체크인 날짜
    private final LocalDate checkOut; // 체크아웃 날짜
    private final int guestCount; // 인원 수
    private final int totalPrice; // 총 가격
    private final ReservationStatus status; // 예약 상태(예약 완료, 취소, 보류)

}
