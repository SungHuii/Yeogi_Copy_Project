package copy.project.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 22.
 */

@Getter
@RequiredArgsConstructor
public class EventDTO {

    private final Long id;
    private final String title;
    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String imageUrl;

}
