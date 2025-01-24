package copy.project.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 24.
 */

@Getter
@RequiredArgsConstructor
public class CouponDTO {

    private final Long id;
    private final Long memberId;
    private final String code;
    private final int discountAmount;
    private final LocalDateTime expiryDate;
}
