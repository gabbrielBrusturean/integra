package integra.backend.registration;

import integra.backend.registration.model.Registration;
import integra.backend.registration.model.RegistrationDto;
import integra.backend.registration.model.UserRegistrationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public RegistrationDto toDto(Registration r) {
        if (r == null) return null;
        
        return new RegistrationDto(
            r.getId(),
            r.getUser().getId(),
            r.getEvent().getId(),
            r.getRegisteredAt(),
            r.getUpdatedAt(),
            r.getStatus()
        );
    }

    public Registration toEntity(RegistrationDto dto) {
        if (dto == null) 
            return null;
        Registration registration = new Registration();
        registration.setId(dto.getId());
        registration.setStatus(dto.getStatus());
        return registration;
    }

    public UserRegistrationResponseDto toUserRegistrationResponse(Registration r) {
        if (r == null) return null;

        return new UserRegistrationResponseDto(
            r.getEvent().getId(),
            r.getEvent().getTitle(),
            r.getEvent().getStartAt(),
            r.getEvent().getEndAt(),
            r.getEvent().getLocation(),
            r.getRegisteredAt(),
            r.getStatus()
        );
    }
}