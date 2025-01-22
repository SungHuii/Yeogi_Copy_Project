package copy.project.demo.domain;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by SungHui on 2025. 1. 22.
 */

@Entity
public class Accommodation { // 숙소 정보

    @Id @GeneratedValue
    @Column(name = "accommodation_id")
    private int id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    private String address;
    
    private DecimalFormat latitude; // 위도
    
    private DecimalFormat longitude; // 경도
    
    private String image_url;
    
    private Date created_at; // 숙소 등록 일자
}
