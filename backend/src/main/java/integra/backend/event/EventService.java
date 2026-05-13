package integra.backend.event;

import integra.backend.event.model.Event;
import integra.backend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " was not found"));
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public void deleteById(Long id) {
        Event existing = getById(id);
        eventRepository.delete(existing);
    }

    public Event update(Long id, Event updated) {
        Event existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setLocation(updated.getLocation());
        existing.setStartAt(updated.getStartAt());
        existing.setEndAt(updated.getEndAt());
        existing.setMaxParticipants(updated.getMaxParticipants());
        return eventRepository.save(existing);
    }
}
