package integra.backend.event.model;

import java.time.LocalDateTime;

public record MyEventResponseDto(
        Long id,
        String title,
        String location,
        LocalDateTime startAt,
        LocalDateTime endAt,
        int registeredParticipants,
        Integer maxParticipants
) {}