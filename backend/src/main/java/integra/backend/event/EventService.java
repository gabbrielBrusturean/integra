package integra.backend.event;
import integra.backend.event.model.Event;
import integra.backend.registration.RegistrationService;
import integra.backend.registration.model.Registration;
import integra.backend.exception.ResourceNotFoundException;
import integra.backend.exception.DuplicateResourceException;
import integra.backend.user.UserRepository;
import integra.backend.user.model.User;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final RegistrationService registrationService;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, RegistrationService registrationService, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.registrationService = registrationService;
        this.userRepository = userRepository;
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

    public void registerVolunteer(Long eventId, Long volunteerId) {
        registrationService.create(volunteerId, eventId, new Registration());
    }

    
    public List<User> getRegisteredVolunteers(Long eventId, String search) {
        List<User> eventSpecificList = registrationService.getByEventId(eventId).stream()
                .map(Registration::getUser)
                .toList();

        if (search == null || search.trim().length() < 3) {
            return eventSpecificList;
        }

        String query = search.trim().replaceAll("\\s+", " ").toLowerCase();
        return eventSpecificList.stream()
            .filter(u -> {
                String firstName = u.getFirstName() != null ? u.getFirstName().toLowerCase() : "";
                String lastName = u.getLastName() != null ? u.getLastName().toLowerCase() : "";
                String fullName = firstName + " " + lastName;
                String email = u.getEmail() != null ? u.getEmail().toLowerCase() : "";

                boolean matchName = firstName.contains(query) || lastName.contains(query);
                boolean matchFullName = fullName.contains(query);
                boolean matchEmail = email.contains(query);

                return matchName || matchFullName || matchEmail;
            })
            .toList();
    }
}
