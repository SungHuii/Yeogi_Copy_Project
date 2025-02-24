package copy.project.demo.dto;

import copy.project.demo.entity.enums.AccommodationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 숙소 DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {

    private  Long id; // 식별자 값
    private  String name; // 숙소명
    private  String description; // 숙소 설명
    private  AccommodationType type; // 숙소 타입
    private  String address; // 숙소 주소
    private  BigDecimal latitude; // 위도
    private  BigDecimal longitude; // 경도
    private  String imageUrl; // 숙소 이미지
    private  List<AccommodationRoomDTO> roomList; // 숙소 방 리스트

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
