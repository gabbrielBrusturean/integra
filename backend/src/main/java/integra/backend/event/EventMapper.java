package integra.backend.event;

import integra.backend.event.model.Event;
import integra.backend.event.model.EventDto;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public EventDto toDto(Event e) {
        if (e == null)
            return null;
        return new EventDto(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                e.getLocation(),
                e.getStartAt(),
                e.getEndAt(),
                e.getCreatedAt(),
                e.getMaxParticipants());
    }

    public Event toEntity(EventDto dto) {
        if (dto == null)
            return null;
        return new Event(
                dto.id(),
                dto.title(),
                dto.description(),
                dto.location(),
                dto.startAt(),
                dto.endAt(),
                dto.createdAt(),
                dto.maxParticipants());
    }
}
