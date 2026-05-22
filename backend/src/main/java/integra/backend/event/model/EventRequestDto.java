package integra.backend.event.model;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventRequestDto(
                @NotBlank(message = "title is required") @Size(max = 255,
                                message = "title must be at most 255 characters") String title,
                String description, String location,
                @NotNull(message = "startAt is required") LocalDateTime startAt,
                @NotNull(message = "endAt is required") LocalDateTime endAt,
                Integer maxParticipants) {
}
