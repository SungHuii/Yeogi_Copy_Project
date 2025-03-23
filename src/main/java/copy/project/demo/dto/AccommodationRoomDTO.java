package copy.project.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 숙소 내 방 DTO */
@Getter
@RequiredArgsConstructor
public class AccommodationRoomDTO {

    private final Long id; // 식별자 값
    private final Long accommodationId; // 연결된 숙소 식별자 값
    private final String roomType; // 방 타입
    private final int maxOccupancy; // 최대 수용 인원
    private final int price; // 가격

}
