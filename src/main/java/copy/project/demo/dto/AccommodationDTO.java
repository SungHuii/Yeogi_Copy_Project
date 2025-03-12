package copy.project.demo.dto;

import copy.project.demo.entity.enums.AccommodationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 숙소 DTO */
@Getter
@RequiredArgsConstructor
public class AccommodationDTO {

    private final Long id; // 식별자 값
    private final String name; // 숙소명
    private final String description; // 숙소 설명
    private final AccommodationType type; // 숙소 타입
    private final String address; // 숙소 주소
    private final BigDecimal latitude; // 위도
    private final BigDecimal longitude; // 경도
    private final String imageUrl; // 숙소 이미지
    private final List<AccommodationRoomDTO> roomList; // 숙소 방 리스트

    // Jackson의 역직렬화 과정에 필요한 기본 생성자
    public AccommodationDTO() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.type = null;
        this.address = null;
        this.latitude = null;
        this.longitude = null;
        this.imageUrl = null;
        this.roomList = new ArrayList<>();
    }

    // 빈 리스트로 초기화하는 추가 생성자
    public AccommodationDTO(Long id, String name, String description, AccommodationType type,
                            String address, BigDecimal latitude, BigDecimal longitude,
                            String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.roomList = new ArrayList<>(); // 빈 리스트로 초기화
    }
}
