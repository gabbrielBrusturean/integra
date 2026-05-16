package integra.backend.event.service;
import java.util.List;
import integra.backend.event.model.RegisteredVolunteerDto;

public interface IEventService {
    List<RegisteredVolunteerDto> getRegisteredVolunteers(Long eventId, String search);
}
