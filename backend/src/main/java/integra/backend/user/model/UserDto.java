package integra.backend.user.model;

import java.time.LocalDateTime;

public record UserDto(Long id, String firstName, String lastName, String email, LocalDateTime createdAt) {}
