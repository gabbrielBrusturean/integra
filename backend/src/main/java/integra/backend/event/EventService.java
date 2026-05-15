package integra.backend.event;

import integra.backend.event.model.Event;
import java.util.List;
import java.util.Optional;
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

    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public Optional<Event> update(Long id, Event updated) {
        return eventRepository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setDescription(updated.getDescription());
            existing.setLocation(updated.getLocation());
            existing.setStartAt(updated.getStartAt());
            existing.setEndAt(updated.getEndAt());
            existing.setMaxParticipants(updated.getMaxParticipants());
            return eventRepository.save(existing);
        });
    }
}
