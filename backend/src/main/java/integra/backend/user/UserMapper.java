package integra.backend.user;

import integra.backend.user.model.User;
import integra.backend.user.model.UserResponseDto;
import integra.backend.user.model.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toDto(User u) {
        return new UserResponseDto(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getCreatedAt(), u.getUpdatedAt());
    }

    public User toNewEntity(UserRequestDto dto) {
        return new User(null, dto.firstName(), dto.lastName(), dto.email(), null);
    }

    public User toEntity(UserRequestDto dto) {
        return new User(null, dto.firstName(), dto.lastName(), dto.email(), null);
    }
}
