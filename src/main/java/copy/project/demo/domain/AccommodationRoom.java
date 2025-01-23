package copy.project.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity
@Getter
@Setter
public class AccommodationRoom extends CommonEntity{ // 숙소 방 정보

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodation accommodation;

    /* ENUM 처리 하지 않은 이유는
    * Standard, Deluxe, Premium, Single, Double 등등 종류가 너무 많기 때문 */
    @Column(name = "room_type", nullable = false)
    private String roomType;

    // 최대 수용 인원
    @Column(name = "max_occupancy", nullable = false)
    private int maxOccupancy;

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
