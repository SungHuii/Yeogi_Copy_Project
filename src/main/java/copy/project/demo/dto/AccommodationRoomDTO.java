package copy.project.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by SungHui on 2025. 1. 22.
 */

@Getter
@RequiredArgsConstructor
public class AccommodationRoomDTO {

    private final Long id;
    private final Long accommodationId;
    private final String roomType;
    private final int maxOccupancy;
    private final int price;

}
