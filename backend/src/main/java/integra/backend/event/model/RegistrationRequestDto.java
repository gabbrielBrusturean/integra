package integra.backend.event.model;

import jakarta.validation.constraints.NotNull;

public record RegistrationRequestDto(
    @NotNull(message = "Volunteer ID is required")
    Long volunteerId
) {}
