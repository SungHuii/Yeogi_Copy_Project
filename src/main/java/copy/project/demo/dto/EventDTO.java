package copy.project.demo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 이벤트 DTO */
@Getter

public class EventDTO {

    private Long id; // 식별자 값
    private String title; // 이벤트 명
    private String description; // 이벤트 설명
    private LocalDateTime startDate; // 이벤트 시작 날짜
    private LocalDateTime endDate; // 이벤트 종료 날짜
    private String imageUrl; // 이벤트 이미지

}
