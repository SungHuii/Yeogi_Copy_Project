package copy.project.demo.entity;

import copy.project.demo.dto.AccommodationDTO;
import copy.project.demo.entity.common.CommonEntity;
import copy.project.demo.entity.enums.AccommodationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 연관 관계
*  AccommodationRoom 1 : N Accommodation
* */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation extends CommonEntity { // 숙소 정보

    // 숙소 식별자 값
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 숙소명
    @Column(name = "name", nullable = false)
    private String name;

    // 숙소 설명
    @Lob // Large Object
    @Column(name = "description")
    private String description;

    // 숙소 타입
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccommodationType type; // 호텔, 모텔, 펜션, 게스트하우스

    // 숙소 주소
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude; // 위도

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude; // 경도

    // 숙소 이미지
    @Lob
    @Column(name = "image_url")
    private String imageUrl;

    // 숙소 방 정보
    @OneToMany(mappedBy = "accommodation") // 연관 관계의 주인 X
    private List<AccommodationRoom> roomList;

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", imageUrl='" + imageUrl + '\'' +
                ", roomList=" + roomList +
                '}';
    }

    // 숙소 정보 수정용
    public Accommodation copy(AccommodationDTO dto) {
        return new Accommodation(
                this.id,
                dto.getName(),
                dto.getDescription(),
                dto.getType(),
                dto.getAddress(),
                dto.getLatitude(),
                dto.getLongitude(),
                dto.getImageUrl(),
                new ArrayList<AccommodationRoom>()
        );
    }
}