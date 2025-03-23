package copy.project.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */

/* 이벤트 DTO */
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)  // 기본 생성자 추가 (final 필드 강제 초기화)
public class EventDTO {

    private final Long id; // 식별자 값
    private final String title; // 이벤트 명
    private final String description; // 이벤트 설명
    private final LocalDateTime startDate; // 이벤트 시작 날짜
    private final LocalDateTime endDate; // 이벤트 종료 날짜
    private final String imageUrl; // 이벤트 이미지

}
