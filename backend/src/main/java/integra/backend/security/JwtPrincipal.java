package integra.backend.security;

public record JwtPrincipal(Long userId, String email) {
}
