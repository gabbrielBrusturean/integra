package integra.backend.event.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EventRequestDto(
        @NotBlank(message = "title is required")
        @Size(max = 255, message = "title must be at most 255 characters")
        String title,
        String description,
        @NotBlank(message = "location is required")
        @Size(max = 255, message = "location must be at most 255 characters")
        String location,
        String address,
        @DecimalMin(value = "-90.0")
        @DecimalMax(value = "90.0")
        Double latitude,
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        Double longitude,
        @NotNull(message = "startAt is required")
        LocalDateTime startAt,
        @NotNull(message = "endAt is required")
        LocalDateTime endAt,
        @Positive(message = "maxParticipants must be greater than 0")
        Integer maxParticipants) {

}
