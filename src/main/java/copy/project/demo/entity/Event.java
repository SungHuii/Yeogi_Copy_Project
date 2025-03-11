package copy.project.demo.entity;

import copy.project.demo.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 이벤트 엔티티 */
@Entity
@Getter
@NoArgsConstructor
public class Event extends CommonEntity {

    // 이벤트 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 이벤트 명
    @Column(name = "title", nullable = false)
    private String title;

    // 이벤트 설명
    @Lob
    @Column(name = "description")
    private String description;

    // 이벤트 시작 날짜
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    // 이벤트 종료 날짜
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    // 이벤트 이미지
    @Lob
    @Column(name = "image_url")
    private String imageUrl;

    // 명시적 생성자
    public Event(Long id, String title, String description, LocalDateTime startDate, LocalDateTime endDate, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}