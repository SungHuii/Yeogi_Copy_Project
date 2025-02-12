package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 연관관계
*  Accommodation - AccommodationRoom : 1 : N
* */
@Entity
@Getter
@NoArgsConstructor
public class AccommodationRoom extends CommonEntity { // 숙소 방 정보

    // 숙소 방 정보 식별값
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 숙소 정보
    @ManyToOne // 연관 관계의 주인
    @JoinColumn(name = "accommodation_id", nullable = false) // 외래키
    private Accommodation accommodation;

    // 숙소 방 종류
    /* ENUM 처리 하지 않은 이유는
    * Standard, Deluxe, Premium, Single, Double 등등 종류가 너무 많기 때문 */
    @Column(name = "room_type", nullable = false)
    private String roomType;

    // 최대 수용 인원
    @Column(name = "max_occupancy", nullable = false)
    private int maxOccupancy;

    // 숙소 방 가격
    @Column(name = "price", nullable = false)
    private int price;


    @Override
    public String toString() {
        return "AccommodationRoom{" +
                "id=" + id +
                ", accommodation=" + accommodation +
                ", roomType='" + roomType + '\'' +
                ", maxOccupancy=" + maxOccupancy +
                ", price=" + price +
                '}';
    }
}
