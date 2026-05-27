package integra.backend.event.model;

public record RegisteredVolunteerDto(
    Long id,
    String firstName,
    String lastName,
    String email
) {}
