package copy.project.demo.domain;

import jakarta.persistence.*;

/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity
public class AccommodationRoom { // 숙소 방 정보

    @Id @GeneratedValue
    @Column(name="accommodation_room_id")
    private int id;

    @ManyToOne
    private int accommodationId;

    /* ENUM 처리 하지 않은 이유는
    * Standard, Deluxe, Premium, Single, Double 등등 종류가 너무 많기 때문 */
    private String roomType;

    // 최대 수용 인원
    private int maxOccupancy;

    private int price;

}
