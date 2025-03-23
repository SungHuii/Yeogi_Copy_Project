package copy.project.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 리뷰 DTO */
@Getter
@RequiredArgsConstructor
public class ReviewDTO {

    private final Long id; // 식별자 값
    private final Long memberId; // 리뷰 작성자 식별자 값
    private final Long accommodationId; // 리뷰 숙소 식별자 값
    private final byte rating; // 평점
    private final String comment; // 리뷰 내용
}
