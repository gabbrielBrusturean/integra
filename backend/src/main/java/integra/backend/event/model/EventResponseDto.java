package integra.backend.event.model;

import java.time.LocalDateTime;

public record EventResponseDto(Long id,
        String title,
        String description,
        String location,
        LocalDateTime startAt,
        LocalDateTime endAt,
        LocalDateTime createdAt,
        Integer maxParticipants) {
}
