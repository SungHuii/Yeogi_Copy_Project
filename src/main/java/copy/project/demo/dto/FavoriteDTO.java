package copy.project.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 찜 기능 DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDTO {

    private Long id; // 식별자 값
    private Long memberId; // 연결된 회원 식별자 값
    private Long accommodationId; // 연결된 숙소 식별자 값

}
