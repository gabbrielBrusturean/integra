package integra.backend.registration;

import integra.backend.registration.model.Registration;
import integra.backend.registration.model.RegistrationDto;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {
    public RegistrationDto toDto(Registration r) {
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
        Registration r = new Registration();
        r.setId(dto.getId());
        r.setStatus(dto.getStatus());
        r.setRegisteredAt(dto.getRegisteredAt());
        return r;
    }
}