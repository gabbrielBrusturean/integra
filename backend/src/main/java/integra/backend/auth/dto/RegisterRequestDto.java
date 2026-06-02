package integra.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(@NotBlank @Email @Size(max = 255) String email,
        @NotBlank @Size(min = 8, max = 60, message = "Password must be between 8 and 60 characters") String password,
        @NotBlank @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters") String firstName,
        @NotBlank @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters") String lastName) {
}
