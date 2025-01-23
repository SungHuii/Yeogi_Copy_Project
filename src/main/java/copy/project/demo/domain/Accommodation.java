package copy.project.demo.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 연관 관계
* AccommodationRoom
* */
@Entity
public class Accommodation extends CommonEntity{ // 숙소 정보

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccommodationType type;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude; // 위도

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude; // 경도

    @Lob
    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationRoom> roomList = new ArrayList<>();

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
}
