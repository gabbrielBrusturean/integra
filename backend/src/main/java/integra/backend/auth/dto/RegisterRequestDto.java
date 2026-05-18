package integra.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(@NotBlank @Email String email,
        @NotBlank @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters") String password,
        @NotBlank @Size(min = 1, max = 100,
                message = "First name must be between 1 and 100 characters") String firstName,
        @NotBlank @Size(min = 1, max = 100,
                message = "Last name must be between 1 and 100 characters") String lastName) {
}
