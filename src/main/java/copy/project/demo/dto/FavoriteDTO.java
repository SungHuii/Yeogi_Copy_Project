package copy.project.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 24.
 */

/* 찜 기능 DTO */
@Getter
@RequiredArgsConstructor
public class FavoriteDTO {

    private final Long id; // 식별자 값
    private final Long memberId; // 연결된 회원 식별자 값
    private final Long accommodationId; // 연결된 숙소 식별자 값

}
