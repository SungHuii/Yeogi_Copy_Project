package copy.project.demo.dto;

import copy.project.demo.entity.enums.ReservationStatus;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 24.
 */
/* 예약 정보 DTO */
@Getter

public class ReservationDTO {

    private Long id; // 식별자 값
    private Long memberId; // 예약자 식별자 값
    private Long accommodationRoomId; // 숙소 방 식별자 값
    private LocalDate reservationDate; // 예약 날짜
    private LocalDate checkIn; // 체크인 날짜
    private LocalDate checkOut; // 체크아웃 날짜
    private int guestCount; // 인원 수
    private int totalPrice; // 총 가격
    private ReservationStatus status; // 예약 상태(예약 완료, 취소, 보류)

}
