package copy.project.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 24.
 */

@Getter
@RequiredArgsConstructor
public class ReviewDTO {

    private final Long id;
    private final Long memberId;
    private final Long accommodationId;
    private final byte rating;
    private final String comment;
}
