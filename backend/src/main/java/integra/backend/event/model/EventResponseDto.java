package integra.backend.event.model;

import java.time.LocalDateTime;

public record EventResponseDto(Long id,
        String title,
        String description,
        String location,
        String address,
        Double latitude,
        Double longitude,
        LocalDateTime startAt,
        LocalDateTime endAt,
        LocalDateTime createdAt,
        Integer maxParticipants) {

}
