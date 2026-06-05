package integra.backend.event;

import integra.backend.event.model.Event;
import integra.backend.event.model.EventResponseDto;
import integra.backend.event.model.EventRequestDto;
import integra.backend.event.model.MyEventResponseDto;

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
                dto.endAt(), null, dto.maxParticipants(), null);
    }

    public MyEventResponseDto toMyEventsDto(Event e) {
        if (e == null)
            return null;
        int simulatedParticipants = (e.getId() != null) ? (int) (e.getId() * 3 + 4) : 5;
        if (e.getMaxParticipants() != null && simulatedParticipants > e.getMaxParticipants()) {
            simulatedParticipants = e.getMaxParticipants() - 2;
        }

        return new MyEventResponseDto(
                e.getId(),
                e.getTitle(),
                e.getLocation(),
                e.getStartAt(),
                e.getEndAt(),
                simulatedParticipants,
                e.getMaxParticipants()
        );
    }
}