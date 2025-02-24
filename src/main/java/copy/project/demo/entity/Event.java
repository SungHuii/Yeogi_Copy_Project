package copy.project.demo.entity;

import copy.project.demo.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 이벤트 엔티티 */
@Entity
@Getter
@RequiredArgsConstructor
public class Event extends CommonEntity {

    protected Event () {
        this.id = null;
        this.title = null;
        this.description = null;
        this.startDate = null;
        this.endDate = null;
        this.imageUrl = null;
    }

    // 이벤트 식별자
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    // 이벤트 명
    @Column(name = "title", nullable = false)
    private final String title;

    // 이벤트 설명
    @Lob
    @Column(name = "description")
    private final String description;

    // 이벤트 시작 날짜
    @Column(name = "start_date", nullable = false)
    private final LocalDateTime startDate;

    // 이벤트 종료 날짜
    @Column(name = "end_date", nullable = false)
    private final LocalDateTime endDate;

    // 이벤트 이미지
    @Lob
    @Column(name = "image_url")
    private final String imageUrl;


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