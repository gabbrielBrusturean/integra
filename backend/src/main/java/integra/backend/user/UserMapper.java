package integra.backend.user;

import integra.backend.user.model.User;
import integra.backend.user.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getCreatedAt());
    }

    public User toEntity(UserDto dto) {
        return new User(dto.id(), dto.firstName(), dto.lastName(), dto.email(), dto.createdAt());
    }
}
