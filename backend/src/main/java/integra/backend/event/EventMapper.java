package integra.backend.event;

import integra.backend.event.model.Event;
import integra.backend.event.model.EventResponseDto;
import integra.backend.event.model.EventRequestDto;

import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public EventResponseDto toDto(Event e) {
        if (e == null)
            return null;
        return new EventResponseDto(e.getId(), e.getTitle(), e.getDescription(), e.getLocation(),
                e.getStartAt(), e.getEndAt(), e.getCreatedAt(), e.getMaxParticipants());
    }

    public Event toEntity(EventRequestDto dto) {
        if (dto == null)
            return null;
        return new Event(null, dto.title(), dto.description(), dto.location(), dto.startAt(),
                dto.endAt(), null, dto.maxParticipants());
    }
}
