package copy.project.demo.dto;

import lombok.Getter;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 숙소 내 방 DTO */
@Getter

public class AccommodationRoomDTO {

    private Long id; // 식별자 값
    private Long accommodationId; // 연결된 숙소 식별자 값
    private String roomType; // 방 타입
    private int maxOccupancy; // 최대 수용 인원
    private int price; // 가격

}
