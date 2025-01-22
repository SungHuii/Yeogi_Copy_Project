package copy.project.demo.domain;

import jakarta.persistence.*;

/**
 * Created by SungHui on 2025. 1. 22.
 */
@Entity
public class AccommodationRoom { // 숙소 방 정보

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accommodation_room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodation accommodation;

    /* ENUM 처리 하지 않은 이유는
    * Standard, Deluxe, Premium, Single, Double 등등 종류가 너무 많기 때문 */
    private String roomType;

    // 최대 수용 인원
    private int maxOccupancy;

    private int price;

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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
