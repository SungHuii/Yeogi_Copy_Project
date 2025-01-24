package copy.project.demo.dto;

import copy.project.demo.entity.AccommodationRoom;
import copy.project.demo.entity.enums.AccommodationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SungHui on 2025. 1. 24.
 */

@Getter
@RequiredArgsConstructor
public class AccommodationDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final AccommodationType type;
    private final String address;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String imageUrl;
    private final List<AccommodationRoomDTO> roomList;

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
