package integra.backend.auth.dto;

public record RegisterResponseDto(String token, Long userId, String email) {
}
