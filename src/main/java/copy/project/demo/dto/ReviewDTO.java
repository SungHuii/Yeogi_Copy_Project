package copy.project.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 리뷰 DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long id; // 식별자 값
    private Long memberId; // 리뷰 작성자 식별자 값
    private Long accommodationId; // 리뷰 숙소 식별자 값
    private byte rating; // 평점
    private String comment; // 리뷰 내용
}
